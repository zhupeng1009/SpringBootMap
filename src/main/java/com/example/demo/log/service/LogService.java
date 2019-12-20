package com.example.demo.log.service;

import com.example.demo.common.domin.PageDO;
import com.example.demo.common.domin.Query;
import com.example.demo.log.domin.SysLogDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogService {
	PageDO<SysLogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(List<Long> ids);
}
