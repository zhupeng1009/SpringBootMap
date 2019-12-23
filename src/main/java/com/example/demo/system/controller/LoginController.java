package com.example.demo.system.controller;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.domin.Tree;
import com.example.demo.common.shiro.ShiroUtils;
import com.example.demo.common.utils.MD5Utils;
import com.example.demo.system.domain.MenuDO;
import com.example.demo.system.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 *
 * 登陆相关controller
 */
@Controller
@Slf4j
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MenuService menuService;

    /**
     * 登陆 跳转到 login.html
     * 页面打开不输入详细的地址 直接通过使用shiro来判断是否登陆 未登陆跳转到登陆页面
     * 登陆成功后根据shiroComfig配置来跳转到该请求
     * 查询菜单数据后 返回index.html
     * @return
     */
    @GetMapping({"/", "", "/index"})
    public String showIndex(Model model) {
         List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        logger.info(getUser().getName());
        return "index";
    }

    /**
     * 未登录，跳转到登陆页面
     * @return
     */
    @GetMapping("/login")
    String login() {
        return "login";
    }
    /**
     * 登陆
     * 判断用户密码 ，登陆成功 ，写入shiro
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ReturnResult login(String username, String password) {
        String md5password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, md5password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ReturnResult.ok();
        } catch (AuthenticationException e) {
            return ReturnResult.error("用户或密码错误");
        }
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    /**
     * 主页 修改为跳转到地图页面
     * @return
     */
    @GetMapping("/main")
    String main() {
        return "map";
    }
    /**
     * shiro 未授权跳转到该页面
     * 表示未授权
     *
     * @return
     */
    @GetMapping("/403")
    String error403() {
        return "403";
    }
}
