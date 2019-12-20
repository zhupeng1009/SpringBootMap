package com.example.demo.business.dao;

import com.example.demo.business.domain.PostionDO;

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
 * @date 2019-12-17 14:33:54
 */
@Mapper
public interface PostionMapper {

	@Select("select `id`, `type`, `pnumber`, `speed`, `longitude`, `latitude`, `img`, `add_time` from t_postion where id = #{id}")
	PostionDO get(Long id);
	
	@Select("<script>" +
	"select * from t_postion " + 
			"<where>" + 
		  		  "<if test=\"id != null and id != ''\">"+ "and id = #{id} " + "</if>" + 
		  		  "<if test=\"type != null and type != ''\">"+ "and type = #{type} " + "</if>" + 
		  		  "<if test=\"pnumber != null and pnumber != ''\">"+ "and pnumber = #{pnumber} " + "</if>" + 
		  		  "<if test=\"speed != null and speed != ''\">"+ "and speed = #{speed} " + "</if>" + 
		  		  "<if test=\"longitude != null and longitude != ''\">"+ "and longitude = #{longitude} " + "</if>" + 
		  		  "<if test=\"latitude != null and latitude != ''\">"+ "and latitude = #{latitude} " + "</if>" + 
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
	List<PostionDO> list(Map<String, Object> map);

	
	@Insert("insert into t_postion (`type`, `pnumber`, `speed`, `longitude`, `latitude`, `img`, `add_time`)"
	+ "values (#{type}, #{pnumber}, #{speed}, #{longitude}, #{latitude}, #{img}, #{addTime})")
	int save(PostionDO tPostion);
	
	@Update("<script>"+ 
			"update t_postion " + 
					"<set>" + 
		            "<if test=\"id != null\">`id` = #{id}, </if>" + 
                    "<if test=\"type != null\">`type` = #{type}, </if>" + 
                    "<if test=\"pnumber != null\">`pnumber` = #{pnumber}, </if>" + 
                    "<if test=\"speed != null\">`speed` = #{speed}, </if>" + 
                    "<if test=\"longitude != null\">`longitude` = #{longitude}, </if>" + 
                    "<if test=\"latitude != null\">`latitude` = #{latitude}, </if>" + 
                    "<if test=\"img != null\">`img` = #{img}, </if>" + 
                    "<if test=\"addTime != null\">`add_time` = #{addTime}, </if>" + 
          					"</set>" + 
					"where id = #{id}"+
			"</script>")
	int update(PostionDO tPostion);
	
	@Delete("delete from t_postion where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from t_postion where id in " + 
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{id}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
