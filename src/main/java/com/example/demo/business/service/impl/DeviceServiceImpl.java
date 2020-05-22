package com.example.demo.business.service.impl;

import com.example.demo.business.domain.DeviceVO;
import com.example.demo.common.domin.PageDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.business.dao.DeviceMapper;
import com.example.demo.business.domain.DeviceDO;
import com.example.demo.business.service.DeviceService;



@Service
public class DeviceServiceImpl implements DeviceService {
	@Autowired
	private DeviceMapper deviceMapper;
	
	@Override
	public DeviceVO get(Long id){
		return deviceMapper.get(id);
	}
	
	@Override
	public PageDO<DeviceVO> list(Map<String, Object> map){
		List<DeviceVO>  list=deviceMapper.list(map);
		int total = deviceMapper.count(map);
		PageDO<DeviceVO> page=new PageDO<>();
		page.setTotal(total);
		page.setRows(list);
		return page;
	}
	@Override
	public int save(DeviceDO tDevice){
		return deviceMapper.save(tDevice);
	}
	
	@Override
	public int update(DeviceDO tDevice){
		return deviceMapper.update(tDevice);
	}
	
	@Override
	public int remove(Long id){
		return deviceMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return deviceMapper.batchRemove(ids);
	}
	
}
