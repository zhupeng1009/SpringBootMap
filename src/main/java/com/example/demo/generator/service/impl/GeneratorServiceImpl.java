package com.example.demo.generator.service.impl;

import com.example.demo.generator.dao.GeneratorMapper;
import com.example.demo.generator.service.GeneratorService;
import com.example.demo.generator.utils.GeneratorUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;


@Service
public class GeneratorServiceImpl implements GeneratorService {
	@Autowired
	public GeneratorMapper generatorMapper;

	@Override
	public List<Map<String, Object>> list() {
		List<Map<String, Object>> list = generatorMapper.list();
		return list;
	}

	/**
	 * 生成zip压缩文件
	 * @param tableNames
	 * @return
	 */
	@Override
	public byte[] generatorCode(String[] tableNames) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = generatorMapper.get(tableName);
			//查询列信息
			List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
			//生成代码
			GeneratorUtils.generatorCode(table, columns, zip);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}
