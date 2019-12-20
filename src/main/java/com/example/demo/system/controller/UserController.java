package com.example.demo.system.controller;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.Query;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.domin.SysUserDO;
import com.example.demo.common.utils.MD5Utils;
import com.example.demo.common.utils.PageUtils;
import com.example.demo.system.domain.RoleDO;
import com.example.demo.system.service.RoleService;
import com.example.demo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:user:user")
	@GetMapping
	String user(Model model) {
		return "sys/user/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SysUserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return "sys/user/add";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		SysUserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);
		return "sys/user/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	ReturnResult save(SysUserDO user) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	ReturnResult update(SysUserDO user) {
		// return R.error(1, "演示系统不允许修改");
		if (userService.update(user) > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	ReturnResult remove(Long id) {
		// return R.error("演示系统不允许删除");
		if (userService.remove(id) > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	ReturnResult batchRemove(@RequestParam("ids[]") Long[] userIds) {
		List<Long> Ids = Arrays.asList(userIds);
		int r = userService.batchremove(Ids);
		System.out.println(r);
		if (r > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		return !userService.exit(params);// 存在，不通过，false
	}

	@RequiresPermissions("sys:user:resetPwd")
	@Log("请求更改用户密码")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {
		SysUserDO userDO = new SysUserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return "sys/user/reset_pwd";
	}

	@Log("提交更改用户密码")
	@PostMapping("/resetPwd")
	@ResponseBody
	ReturnResult resetPwd(SysUserDO user) {
		if (1L == user.getUserId()) {
			return ReturnResult.error("演示系统不允许修改管理员密码");
		}
		user.setPassword(MD5Utils.encrypt(userService.get(user.getUserId()).getUsername(), user.getPassword()));
		if (userService.resetPwd(user) > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

}
