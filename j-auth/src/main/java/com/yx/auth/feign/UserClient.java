package com.yx.auth.feign;

import api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author: JST
 * @Date: 2019/4/23 17:49
 */
@FeignClient(value = "j-user-service")
public interface UserClient extends UserApi {

}
