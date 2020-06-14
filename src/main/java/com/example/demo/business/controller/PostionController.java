package com.example.demo.business.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.controller.BaseController;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.exception.BusinessException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.business.domain.PositionDO;
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
	public ReturnResult  list(@RequestParam("type") String type){
		//查询列表数据
		List<PositionDO> tPostionList =new ArrayList<>();
		try {
			 tPostionList = postionService.list(type);
		}catch (BusinessException ex){
			return ReturnResult.error(ex.getMsg());
		}
		return ReturnResult.ok(tPostionList) ;
	}

}
