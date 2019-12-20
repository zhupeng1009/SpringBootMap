package com.example.demo.system.controller;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.system.domain.RoleDO;
import com.example.demo.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "sys/role";
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:role:role")
	@GetMapping()
	public String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@Log("添加角色")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@Log("编辑角色")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("保存角色")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	public ReturnResult save(RoleDO role) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		if (roleService.save(role) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "保存失败");
		}
	}

	@Log("更新角色")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	public ReturnResult update(RoleDO role) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		if (roleService.update(role) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "保存失败");
		}
	}

	@Log("删除角色")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	public ReturnResult save(Long id) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		if (roleService.remove(id) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "删除失败");
		}
	}
}
