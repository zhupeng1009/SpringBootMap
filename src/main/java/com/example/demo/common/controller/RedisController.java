package com.example.demo.common.controller;

import com.example.demo.common.domin.ReturnResult;
import com.example.demo.common.redis.RedisConstants;
import com.example.demo.common.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName: RedisController
 * @Auther: zhangyingqi
 * @Date: 2018/8/29 16:15
 * @Description:
 */
@Controller
@RequestMapping(value="/redis")
public class RedisController extends BaseController {
    @Autowired
    RedisUtil redisUtil;
    @GetMapping
    @ResponseBody
    public ReturnResult getRedis(){
        String res = redisUtil.get("20182018", RedisConstants.datebase1);
        return ReturnResult.ok(res);
    }
    @PostMapping
    @ResponseBody
    public ReturnResult set(){
        String res = redisUtil.set("20182018","你好", RedisConstants.datebase1);
        return ReturnResult.ok(res);
    }


}
