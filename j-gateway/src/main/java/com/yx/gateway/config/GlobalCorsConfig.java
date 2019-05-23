package com.yx.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author: JST
 * @Date: 2019/4/27 9:26
 * 跨域配置
 *
 * 页面访问域名和后端接口地址的域名不一致时,会先发起一个OPTIONS的试探请求
 * 因同源策略将导致无法进行跨域访问,,要设置这个配置
 *
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config = new CorsConfiguration();
        //允许cookie跨域使用
        config.setAllowCredentials(true);
        //允许的跨域域名,这里表示所有域名,真实开发中要写公司所用域名,下同
        config.addAllowedOrigin("*");
        //允许的头信息
        config.addAllowedHeader("*");
        //允许的http方法
        config.addAllowedMethod("*");
        //预检请求的缓存时间（秒）,即在这个时间段里,对于相同的跨域请求不会再预检了
        config.setMaxAge(18000L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);

        return new CorsFilter(source);
    }

//    //gateway版的跨域配置
//    private static final String MAX_AGE = "18000L";
//
//    @Bean
//    public WebFilter corsFilter() {
//        return (ServerWebExchange ctx, WebFilterChain chain) -> {
//            ServerHttpRequest request = ctx.getRequest();
//            if (CorsUtils.isCorsRequest(request)) {
//                HttpHeaders requestHeaders = request.getHeaders();
//                ServerHttpResponse response = ctx.getResponse();
//                HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
//                HttpHeaders headers = response.getHeaders();
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
//                headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders
//                        .getAccessControlRequestHeaders());
//                if(requestMethod != null){
//                    headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
//                }
//                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
//                headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//                headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
//                if (request.getMethod() == HttpMethod.OPTIONS) {
//                    response.setStatusCode(HttpStatus.OK);
//                    return Mono.empty();
//                }
//
//            }
//            return chain.filter(ctx);
//        };
//    }
//
//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return new DefaultServerCodecConfigurer();
//    }
//
//    /**
//     * 如果使用了注册中心（如：Eureka），进行控制则需要增加如下配置
//     */
//    @Bean
//    public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient,
//                                                                        DiscoveryLocatorProperties properties) {
//        return new DiscoveryClientRouteDefinitionLocator(discoveryClient, properties);
//    }
}
