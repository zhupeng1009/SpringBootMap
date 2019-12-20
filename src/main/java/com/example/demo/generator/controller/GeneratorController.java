package com.example.demo.generator.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.PastOrPresent;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RequestMapping("/generator")
@Controller
public class GeneratorController {
    String prefix = "/generator";
    @Autowired
    public GeneratorService generatorService;

    /**
     * 进入生成页面，先查询出表结构信息
     *
     * @return
     */
    @GetMapping()
    public String generator() {
        return prefix + "/list";
    }

    /**
     * 查询所有表结构信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/list")
    List<Map<String, Object>> list() {
        List<Map<String, Object>> list = generatorService.list();
        return list;
    }

    /**
     * 生成代码
     *
     * @param response
     * @param tableName
     * @throws IOException
     */
    @GetMapping("/code/{tableName}")
    public void code(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        String[] tableNames = new String[]{tableName};
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"demo.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 批量生成代码
     *
     * @param response
     * @param tables
     * @throws IOException
     */
    @GetMapping("/batchCode")
    public void batchCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = new String[]{};
        tableNames = JSON.parseArray(tables).toArray(tableNames);
        byte[] data = generatorService.generatorCode(tableNames);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"demo.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}
