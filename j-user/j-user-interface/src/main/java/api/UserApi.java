package api;

import com.yx.user.entity.LoginUser;
import com.yx.user.entity.SysUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
    void register(@RequestParam("user") SysUser user);
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @GetMapping("/user/anon/findUsername")
    LoginUser findByUsername(@RequestParam("username") String username);

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/user/listUser")
    List<SysUser> listUser();



}
