package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsResource;
import com.jq.mall.mbg.model.UmsResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UmsResourceMapper {
    @SelectProvider(type=UmsResourceSqlProvider.class, method="countByExample")
    long countByExample(UmsResourceExample example);

    @DeleteProvider(type=UmsResourceSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsResourceExample example);

    @Delete({
        "delete from ums_resource",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_resource (create_time, name, ",
        "url, description, ",
        "category_id)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{name,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{categoryId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsResource record);

    @InsertProvider(type=UmsResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsResource record);

    @SelectProvider(type=UmsResourceSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsResource> selectByExample(UmsResourceExample example);

    @Select({
        "select",
        "id, create_time, name, url, description, category_id",
        "from ums_resource",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="url", property="url", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="category_id", property="categoryId", jdbcType=JdbcType.BIGINT)
    })
    UmsResource selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsResourceSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsResource record, @Param("example") UmsResourceExample example);

    @UpdateProvider(type=UmsResourceSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsResource record, @Param("example") UmsResourceExample example);

    @UpdateProvider(type=UmsResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsResource record);

    @Update({
        "update ums_resource",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "category_id = #{categoryId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsResource record);
}