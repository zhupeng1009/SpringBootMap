package com.example.demo.business.service;

import com.example.demo.business.domain.PositionDO;
import com.example.demo.common.exception.BusinessException;

import java.util.List;

/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:54
 */
public interface PostionService {
	
	PositionDO get(Long id);
	
	List<PositionDO> list() throws BusinessException;

	int save(PositionDO tPostion);
	
	int update(PositionDO tPostion);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
