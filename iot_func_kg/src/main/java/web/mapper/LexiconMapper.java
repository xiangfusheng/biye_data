package web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import web.domain.Lexicon;

public interface LexiconMapper {
    @Select("SELECT * FROM lexicon")
    @Results(
    	id = "lexiconMap",
        value = {
			@Result(property = "id",  column = "id"),
	        @Result(property = "lexicon", column = "lexicon"),
	        @Result(property = "isFunction",  column = "isFunction"),
	        @Result(property = "baseFunctionId",  column = "baseFunctionId"),
	        @Result(property = "isSet",  column = "isSet"),
	        @Result(property = "explained", column = "explained")	
    	})
    List<Lexicon> getAll();
   
    @Select("SELECT * FROM lexicon limit #{from}, #{pageNums}")
    @ResultMap("lexiconMap")
    List<Lexicon> getPage(@Param("from")Integer from, @Param("pageNums")Integer pageNums);
    
    @Select("SELECT * FROM lexicon WHERE isFunction=1 limit #{from}, #{pageNums}")
    @ResultMap("lexiconMap")
    List<Lexicon> getPageIsFunction(@Param("from")Integer from, @Param("pageNums")Integer pageNums);
    
    @Select("<script> SELECT COUNT(*) FROM lexicon WHERE isFunction = #{isFunction}  </script>")
    int count(@Param("isFunction")Integer isFunction);
    
    @Select("SELECT * FROM lexicon WHERE id = #{id}")
    @ResultMap("lexiconMap")
    Lexicon getById(@Param("id")int id);
    
    @Update("UPDATE lexicon SET isFunction=#{lexicon.isFunction}, isSet=#{lexicon.isSet}, baseFunctionId=#{lexicon.baseFunctionId} WHERE id=#{lexicon.id}")
    void update(@Param("lexicon")Lexicon lexicon);
    
}
