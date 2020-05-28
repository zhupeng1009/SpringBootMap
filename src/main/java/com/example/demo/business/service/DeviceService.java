package com.example.demo.business.service;

import com.example.demo.business.domain.DeviceDO;
import com.example.demo.business.domain.DeviceVO;
import com.example.demo.common.domin.PageDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:39
 */
public interface DeviceService {

	DeviceVO get(Long id);
	
	PageDO<DeviceVO> list(Map<String, Object> map);
	
	int save(DeviceDO tDevice);
	
	int update(DeviceDO tDevice);
	int updateByIp(String ip,String status);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
