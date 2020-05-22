package com.example.demo.business.controller;

import java.util.List;
import java.util.Map;

import com.example.demo.business.domain.DeviceDO;
import com.example.demo.business.domain.DeviceVO;
import com.example.demo.common.domin.PageDO;
import com.example.demo.common.domin.Query;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.utils.PageUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.business.service.DeviceService;

/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:39
 */
@Controller
@RequestMapping("/device")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping()
	@RequiresPermissions("business:device")
	String device(){
	    return "business/device/device";
	}
	
	@ResponseBody
	@GetMapping("/list")
	public PageDO<DeviceVO> list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		return deviceService.list(query);
	}

	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public ReturnResult info(@PathVariable("id") Long id){
		DeviceVO tDevice = deviceService.get(id);
		return ReturnResult.ok().put("tDevice", tDevice);
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("generator:save")
	public ReturnResult save( DeviceDO tDevice){
		if(deviceService.save(tDevice)>0){
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("generator:update")
	public ReturnResult update(@RequestBody DeviceDO tDevice){
		deviceService.update(tDevice);
		return ReturnResult.ok();
	}


	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("generator:remove")
	public ReturnResult remove( Long id){
		if(deviceService.remove(id)>0){
		return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("generator:remove")
	public ReturnResult remove(@RequestParam("ids[]") Long[] ids){
		deviceService.batchRemove(ids);

		return ReturnResult.ok();
	}

}
