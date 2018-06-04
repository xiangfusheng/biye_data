package web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import web.domain.Lexicon;

public interface LexiconMapper {
    @Select("SELECT * FROM lexicon")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "lexicon", column = "lexicon"),
        @Result(property = "isFunction",  column = "isFunction"),
        @Result(property = "explained", column = "explained")
    })
    List<Lexicon> getAll();
   
    @Select("SELECT * FROM lexicon limit #{from}, #{pageNums}")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "lexicon", column = "lexicon"),
        @Result(property = "isFunction",  column = "isFunction"),
        @Result(property = "explained", column = "explained")
    })
    List<Lexicon> getPage(@Param("from")Integer from, @Param("pageNums")Integer pageNums);
    
    @Select("SELECT COUNT(*) FROM lexicon")
    int count();
    
    @Select("SELECT * FROM lexicon WHERE id = #{id}")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "lexicon", column = "lexicon"),
        @Result(property = "isFunction",  column = "isFunction"),
        @Result(property = "explained", column = "explained")
    })
    Lexicon getById(@Param("id")int id);
    
    @Update("UPDATE lexicon SET isFunction=#{lexicon.isFunction}, isSet=#{lexicon.isSet} WHERE id=#{lexicon.id}")
    void update(@Param("lexicon")Lexicon lexicon);
    
}
