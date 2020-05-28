package com.example.demo.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.config.ThreadConfig;
import com.example.demo.common.config.UrlConfig;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import com.example.demo.business.dao.PostionMapper;
import com.example.demo.business.domain.PositionDO;
import com.example.demo.business.service.PostionService;


@Service
@Slf4j
public class PostionServiceImpl implements PostionService {
	@Autowired
	private PostionMapper postionMapper;
	@Autowired
	private UrlConfig urlConfig;
	@Autowired
	private ThreadConfig threadConfig;

	private  final  String HTTP="http://";
	private  final  String SPLIT=":";
	
	@Override
	public PositionDO get(Long id){
		return postionMapper.get(id);
	}
	
	@Override
	public List<PositionDO> list()throws BusinessException{

		return getPosition();
	}
	@Override
	public int save(PositionDO tPostion){
		return postionMapper.save(tPostion);
	}
	
	@Override
	public int update(PositionDO tPostion){
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

	private PositionDO getAirPlane(){
		String  url=HTTP+urlConfig.getAriPlaneIP()+SPLIT+urlConfig.getAriPlanePort()+urlConfig.getAriPlaneUrl();
		String  result= HttpUtil.send(url,"GET",null);
		List<PositionDO>	list=JSON.parseArray(result,PositionDO.class);
		PositionDO vo=list.get(0);
		return  vo;
	}
	private PositionDO getBoat(){
		String  url=HTTP+urlConfig.getBoatIP()+SPLIT+urlConfig.getBoatPort()+urlConfig.getBoatURl();;
		String  result= HttpUtil.send(url,"GET",null);
		List<PositionDO>	list=JSON.parseArray(result,PositionDO.class);
		PositionDO vo=list.get(0);
		return  vo;
	}

	private PositionDO getCar(){
		String  url=HTTP+urlConfig.getCarIP()+SPLIT+urlConfig.getCarPort()+urlConfig.getCarUrl();
		String  result= HttpUtil.send(url,"GET",null);
		List<PositionDO>	list=JSON.parseArray(result,PositionDO.class);
		PositionDO vo=list.get(0);
		return  vo;
	}

	private List<PositionDO> getPosition() throws BusinessException {
		List<PositionDO> list=new ArrayList<>();
		FutureTask<PositionDO> airplane = new FutureTask<>(() -> getAirPlane());
		FutureTask<PositionDO> boat = new FutureTask<>(() -> getBoat());
		FutureTask<PositionDO> car = new FutureTask<>(() -> getCar());
		ThreadPoolTaskExecutor task=threadConfig.taskExecutor();
		task.execute(airplane);
		task.execute(boat);
		task.execute(car);
		try {
			list.add(airplane.get());
		}
		catch (InterruptedException | ExecutionException ex){
			Thread.interrupted();
		}
		try {
			list.add(boat.get());
		}
		catch (InterruptedException  | ExecutionException ex){
			//初选异常 让线程继续走
			Thread.interrupted();
		}
		try {
			list.add(car.get());
		}
		catch (InterruptedException | ExecutionException ex){
			Thread.interrupted();
		}
		return list;
	}
	
}
