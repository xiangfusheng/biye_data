package web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import web.domain.BaseFunction;

public interface BaseFunctionMapper {
    @Select("SELECT * FROM base_function")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "baseFunction", column = "base_function"),
    })
    List<BaseFunction> getAll();
}
