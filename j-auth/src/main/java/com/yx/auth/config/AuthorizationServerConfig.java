package com.yx.auth.config;

import com.yx.auth.service.RandomAuthenticationKeyGenerator;
import com.yx.auth.service.RedisAuthorizationCodeServices;
import com.yx.auth.service.RedisClientDetailsService;
import com.yx.user.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;

/**
 * @Author: JST
 * @Date: 2019/4/19 9:31
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 这个是认证管理器
     *
     * @see SecurityConfig 的authenticationManagerBean()
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public UserDetailsService userDetailsService;

    /**
     * 这个是在授权码模式下存储授权码的
     */
    @Autowired
    private RedisAuthorizationCodeServices redisAuthorizationCodeServices;

    /**
     * 这个是用来将oauth_client_details表数据缓存到redis
     */
    @Autowired
    private RedisClientDetailsService redisClientDetailsService;

    /**
     * 使用jwt或redis的token
     * 在这先使用redis的
     */
    @Value("${access_token.store-jwt:false}")
    private boolean storeWithJwt;

    /**
     * 登陆后返回的json数据是否追加当前用户信息<br>
     * 默认false
     */
    @Value("${access_token.add-userinfo:false}")
    private boolean addUserInfo;

    /**
     * jwt签名key，可随意指定<br>
     * 如配置文件里不设置的话，冒号后面的是默认值
     */
    @Value("${access_token.jwt-signing-key:jst1217hahahaha}")
    private String signingKey;

    /**
     * 令牌存储,分为jwt和redis两种形式
     */
    @Bean
    public TokenStore tokenStore() {
        if (storeWithJwt) {//使用jwtToken
            return new JwtTokenStore(accessTokenConverter());
        }
        //使用redis,默认token为uuid
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //解决同一username每次登陆access_token都相同的问题
        redisTokenStore.setAuthenticationKeyGenerator(new RandomAuthenticationKeyGenerator());
        return redisTokenStore;
    }

    /**
     * Jwt资源令牌转换器<br>
     * 参数access_token.store-jwt为true时用到
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2AccessToken oAuth2AccessToken = super.enhance(accessToken, authentication);
                addLoginUserInfo(oAuth2AccessToken, authentication); //将当前用户信息追加到登陆后返回数据里
                return oAuth2AccessToken;
            }
        };
        DefaultAccessTokenConverter defaultAccessTokenConverter
                = (DefaultAccessTokenConverter) jwtAccessTokenConverter.getAccessTokenConverter();
        DefaultUserAuthenticationConverter userAuthenticationConverter = new DefaultUserAuthenticationConverter();
        userAuthenticationConverter.setUserDetailsService(userDetailsService);
        defaultAccessTokenConverter.setUserTokenConverter(userAuthenticationConverter);
        //这里务必设置一个，否则多台认证中心的话，一旦使用jwt方式，access_token将解析错误
        jwtAccessTokenConverter.setSigningKey(signingKey);
        return jwtAccessTokenConverter;
    }

    /**
     * 将当前用户信息追加到登陆后返回的json数据里
     * 通过参数access_token.add-userinfo控制
     *
     * @param oAuth2AccessToken
     * @param authentication
     */
    private void addLoginUserInfo(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication authentication) {
        //如果配置不返回用户信息则直接返回
        if (!addUserInfo) return;
        //将当前用户信息追加到登陆后返回的json数据里
        if (oAuth2AccessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken defaultOAuth2AccessToken = (DefaultOAuth2AccessToken) oAuth2AccessToken;
            Authentication userAuthentication = authentication.getUserAuthentication();
            Object principal = userAuthentication.getPrincipal();
            if (principal instanceof LoginUser) {
                LoginUser user = (LoginUser) principal;
                HashMap<String, Object> map = new HashMap<>(defaultOAuth2AccessToken.getAdditionalInformation());
                map.put("userInfo", user);//追加当前用户
                defaultOAuth2AccessToken.setAdditionalInformation(map);
            }


        }

    }

    /**
     * 允许表单形式的认证
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients();
    }

    /**
     * 我们将client信息存储到oauth_client_details表里
     * 并将数据缓存到redis
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        //测试阶段可以这么玩，表示在内存中创建client，正式使用时一般使用数据库存储client信息
//        clients.inMemory()
//                .withClient("clientapp")
//                .secret("112233")
//                .redirectUris("www.baidu.com")
//                .authorizedGrantTypes("authorization_code,"password","refresh_token"")
//                .scopes("read_info");

        //使用数据库存储client信息
        clients.withClientDetails(redisClientDetailsService);
        //将数据缓存到redis
        redisClientDetailsService.loadAllClientToCache();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(tokenStore());
        // 授权码模式下，code存储(原来使用的是jdbc数据库存储)
// 		endpoints.authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
        endpoints.authorizationCodeServices(redisAuthorizationCodeServices);
        if(storeWithJwt){
            endpoints.accessTokenConverter(accessTokenConverter());
        } else {
            endpoints.tokenEnhancer((accessToken,authentication) ->{
                addLoginUserInfo(accessToken,authentication);
                return accessToken;
            });
        }
    }
}
