/**
 * 
 */
package com.example.demo.generator.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 *代码生成interface
 */
@Service
public interface GeneratorService {
	/**
	 * 查询表结构信息
	 * @return
	 */
	List<Map<String, Object>> list();

	/**
	 * 生成代码inf
	 * @param tableNames
	 * @return
	 */
	byte[] generatorCode(String[] tableNames);
}
