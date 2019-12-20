package com.example.demo.common.controller;/**
 * Created by zhupeng01 on 2019/10/21.
 */

import com.example.demo.common.domin.SysUserDO;
import com.example.demo.common.shiro.ShiroUtils;

/**
 * 获取用户信息公共类
 * 通过shiro 获取
 * @author zhupeng
 * @date 2019-10-21 17:59
 */
public class BaseController {
    public SysUserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }
}
