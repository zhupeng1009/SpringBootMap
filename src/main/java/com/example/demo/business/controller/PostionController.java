package com.example.demo.business.controller;

import java.util.List;
import java.util.Map;

import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.Query;
import com.example.demo.common.domin.ReturnResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.business.domain.PostionDO;
import com.example.demo.business.service.PostionService;

/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:54
 */
@Controller
public class PostionController extends BaseController {
	@Autowired
	private PostionService postionService;
	
	@GetMapping("/position")
	@RequiresPermissions("business:position:show")
	String show(){
	    return "map";
	}
	@ResponseBody
	@GetMapping("/position/list")
	@RequiresPermissions("business:position:list")
	public List<PostionDO>  list(){
		//查询列表数据
		List<PostionDO> tPostionList = postionService.list();
		return tPostionList ;
	}

}
