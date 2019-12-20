package com.example.demo.system.controller;

import com.example.demo.common.annotation.Log;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.domin.Tree;
import com.example.demo.system.domain.MenuDO;
import com.example.demo.system.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/menu")
@Controller
public class MenuController extends BaseController {
	@Autowired
	MenuService menuService;

	@RequiresPermissions("sys:menu:menu")
	@GetMapping()
	String menu(Model model) {
		return "sys/menu/menu";
	}

	@RequiresPermissions("sys:menu:menu")
	@GetMapping("/list")
	@ResponseBody
	List<MenuDO> list() {
		List<MenuDO> menus = menuService.list();
		return menus;
	}

	@Log("添加菜单")
	@RequiresPermissions("sys:menu:add")
	@GetMapping("/add/{pId}")
	public String add(Model model, @PathVariable("pId") Long pId) {
		model.addAttribute("pId", pId);
		if (pId == 0) {
			model.addAttribute("pName", "根目录");
		} else {
			model.addAttribute("pName", menuService.get(pId).getName());
		}
		return "sys/menu/add";
	}

	@Log("编辑菜单")
	@RequiresPermissions("sys:menu:edit")
	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable("id") Long id) {
		model.addAttribute("menu", menuService.get(id));
		return "sys/menu/edit";
	}

	@Log("删除菜单")
	@RequiresPermissions("sys:menu:remove")
	@PostMapping("/remove")
	@ResponseBody
	public ReturnResult remove(Long id) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		
		if (menuService.remove(id) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "删除失败");
		}
	}

	@Log("保存菜单")
	@RequiresPermissions("sys:menu:add")
	@PostMapping("/save")
	@ResponseBody
	public ReturnResult save(MenuDO menu) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		
		if (menuService.save(menu) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "保存失败");
		}
	}

	@Log("更新菜单")
	@RequiresPermissions("sys:menu:edit")
	@PostMapping("/update")
	@ResponseBody
	public ReturnResult update(MenuDO menu) {
		if ("test"==getUsername()) {
			return ReturnResult.error(1, "演示系统不允许删除,完整体验请部署程序");
		}
		if (menuService.update(menu) > 0) {
			return ReturnResult.ok();
		} else {
			return ReturnResult.error(1, "更新失败");
		}
	}

	@GetMapping("/tree")
	@ResponseBody
	public Tree<MenuDO> tree() {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree();
		return tree;
	}

	@GetMapping("/tree/{roleId}")
	@ResponseBody
	public Tree<MenuDO> tree(@PathVariable("roleId") Long roleId) {
		Tree<MenuDO> tree = new Tree<MenuDO>();
		tree = menuService.getTree(roleId);
		return tree;
	}
}
