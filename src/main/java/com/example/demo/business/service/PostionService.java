package com.example.demo.business.service;

import com.example.demo.business.domain.PostionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:54
 */
public interface PostionService {
	
	PostionDO get(Long id);
	
	List<PostionDO> list();

	int save(PostionDO tPostion);
	
	int update(PostionDO tPostion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
