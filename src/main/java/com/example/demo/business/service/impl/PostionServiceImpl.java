package com.example.demo.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.example.demo.business.dao.PostionMapper;
import com.example.demo.business.domain.PostionDO;
import com.example.demo.business.service.PostionService;



@Service
public class PostionServiceImpl implements PostionService {
	@Autowired
	private PostionMapper postionMapper;
	
	@Override
	public PostionDO get(Long id){
		return postionMapper.get(id);
	}
	
	@Override
	public List<PostionDO> list(Map<String, Object> map){
		return postionMapper.list(map);
	}
	@Override
	public int save(PostionDO tPostion){
		return postionMapper.save(tPostion);
	}
	
	@Override
	public int update(PostionDO tPostion){
		return postionMapper.update(tPostion);
	}
	
	@Override
	public int remove(Long id){
		return postionMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return postionMapper.batchRemove(ids);
	}
	
}
