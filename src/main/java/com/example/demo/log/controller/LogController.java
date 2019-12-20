package com.example.demo.log.controller;

import com.example.demo.common.domin.PageDO;
import com.example.demo.common.domin.Query;
import com.example.demo.common.domin.ReturnResult;
import com.example.demo.log.domin.SysLogDO;
import com.example.demo.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RequestMapping("/log")
@Controller
@Slf4j
public class LogController {
	@Autowired
	LogService logService;
	String prefix = "/log";

	@GetMapping()
	String log() {
		return prefix + "/log";
	}

	@ResponseBody
	@GetMapping("/list")
	PageDO<SysLogDO> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		PageDO<SysLogDO> page = logService.queryList(query);
		return page;
	}
	
	@ResponseBody
	@PostMapping("/remove")
	ReturnResult remove(Long id) {
		if (logService.remove(id)>0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	ReturnResult batchRemove(@RequestParam("ids[]") Long[] idsArray) {
		List<Long> ids = Arrays.asList(idsArray);
		int r = logService.batchRemove(ids);
		if (r > 0) {
			return ReturnResult.ok();
		}
		return ReturnResult.error();
	}
}
