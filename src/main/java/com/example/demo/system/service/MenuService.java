package com.example.demo.system.service;

import com.example.demo.common.domin.Tree;
import com.example.demo.system.domain.MenuDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 1992lcg@163.com
 * @Time 2017年8月21日
 * @description 系统菜单业务代码
 * 
 */
@Service
public interface MenuService {
	Tree<MenuDO> getSysMenuTree(Long id);
	List<Tree<MenuDO>> listMenuTree(Long id);
	Tree<MenuDO> getTree();
	Tree<MenuDO> getTree(Long id);
	List<MenuDO> list();
	int remove(Long id);
	int save(MenuDO menu);
	int update(MenuDO menu);
	MenuDO get(Long id);
	Set<String> listPerms(Long userId);
}
