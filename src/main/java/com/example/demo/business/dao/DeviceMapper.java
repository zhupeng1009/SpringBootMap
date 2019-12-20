package com.example.demo.business.dao;

import com.example.demo.business.domain.DeviceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
/**
 * 
 * 
 * @author zhupeng
 * @email 250817571@qq.com
 * @date 2019-12-17 14:33:39
 */
@Mapper
public interface DeviceMapper {

	@Select("select `id`, `node_name`, `ip_address`, `status`, `img`, `add_time` from t_device where id = #{id}")
	DeviceDO get(Long id);
	
	@Select("<script>" +
	"select * from t_device " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"nodeName != null and nodeName != ''\">"+ "and node_name = #{nodeName} " + "</if>" + 
		  		  "<if test=\"ipAddress != null and ipAddress != ''\">"+ "and ip_address = #{ipAddress} " + "</if>" + 
		  		  "<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" + 
		  		  "<if test=\"img != null and img != ''\">"+ "and img = #{img} " + "</if>" + 
		  		  "<if test=\"addTime != null and addTime != ''\">"+ "and add_time = #{addTime} " + "</if>" + 
		  			"</where>"+ 
			" <choose>" + 
	            "<when test=\"sort != null and sort.trim() != ''\">" + 
	                "order by ${sort} ${order}" + 
	            "</when>" + 
				"<otherwise>" + 
	                "order by id desc" + 
				"</otherwise>" + 
	        "</choose>"+
			"<if test=\"offset != null and limit != null\">"+
			"limit #{offset}, #{limit}" + 
			"</if>"+
			"</script>")
	List<DeviceDO> list(Map<String, Object> map);

	@Select("<script>" +
			"select count(*) from t_device " +
			"<where>" +
			"<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" +
			"<if test=\"nodeName != null and nodeName != ''\">"+ "and node_name = #{nodeName} " + "</if>" +
			"<if test=\"ipAddress != null and ipAddress != ''\">"+ "and ip_address = #{ipAddress} " + "</if>" +
			"<if test=\"status != null and status != ''\">"+ "and status = #{status} " + "</if>" +
			"<if test=\"img != null and img != ''\">"+ "and img = #{img} " + "</if>" +
			"<if test=\"addTime != null and addTime != ''\">"+ "and add_time = #{addTime} " + "</if>" +
			"</where>"+
			"</script>")
	int count(Map<String,Object> map);
	@Insert("insert into t_device (`id`, `node_name`, `ip_address`, `status`, `img`, `add_time`)"
	+ "values (#{id}, #{nodeName}, #{ipAddress}, #{status}, #{img}, #{addTime})")
	int save(DeviceDO tDevice);
	
	@Update("<script>"+ 
			"update t_device " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" + 
                    "<if test=\"nodeName != null\">`node_name` = #{nodeName}, </if>" + 
                    "<if test=\"ipAddress != null\">`ip_address` = #{ipAddress}, </if>" + 
                    "<if test=\"status != null\">`status` = #{status}, </if>" + 
                    "<if test=\"img != null\">`img` = #{img}, </if>" + 
                    "<if test=\"addTime != null\">`add_time` = #{addTime}, </if>" + 
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(DeviceDO tDevice);
	
	@Delete("delete from t_device where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from t_device where id in " + 
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{id}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
