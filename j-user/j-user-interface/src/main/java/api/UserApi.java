package api;

import com.yx.user.pojo.LoginUser;
import com.yx.user.pojo.UserDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * 这样设置便于模块化管理，其他模块调用此模块时可直接继承此接口即可
 */
public interface UserApi {
    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/user/register")
    void register(@RequestParam("user") @Valid UserDO user);
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/user/findByUsername")
    LoginUser findByUsername(@RequestParam("username") String username);


}
