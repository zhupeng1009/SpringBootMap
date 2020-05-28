package com.example.demo.business.dao;

import com.example.demo.business.domain.PositionDO;

import java.util.List;

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

	@Select("select `id`, `type`, `pnumber`, `speed`, `longitude`, `latitude`, `img`, `add_time` from t_position where id = #{id}")
    PositionDO get(Long id);
	
	@Select("select * from t_position ")
	List<PositionDO> list();

	
	@Insert("insert into t_position (`type`, `pnumber`, `speed`, `longitude`, `latitude`, `img`, `add_time`)"
	+ "values (#{type}, #{pnumber}, #{speed}, #{longitude}, #{latitude}, #{img}, #{addTime})")
	int save(PositionDO tPostion);
	
	@Update("<script>"+ 
			"update t_position " +
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
	int update(PositionDO tPostion);
	
	@Delete("delete from t_position where id =#{id}")
	int remove(Long id);
	
	@Delete("<script>"+ 
			"delete from t_position where id in " +
					"<foreach item=\"id\" collection=\"array\" open=\"(\" separator=\",\" close=\")\">" + 
						"#{id}" + 
					"</foreach>"+
			"</script>")
	int batchRemove(Long[] ids);
}
