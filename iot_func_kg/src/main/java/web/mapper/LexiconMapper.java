package web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
}
