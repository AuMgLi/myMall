package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsPermission;
import com.jq.mall.mbg.model.UmsPermissionExample;
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

public interface UmsPermissionMapper {
    @SelectProvider(type=UmsPermissionSqlProvider.class, method="countByExample")
    long countByExample(UmsPermissionExample example);

    @DeleteProvider(type=UmsPermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsPermissionExample example);

    @Delete({
        "delete from ums_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_permission (pid, name, ",
        "value, icon, type, ",
        "uri, status, create_time, ",
        "sort)",
        "values (#{pid,jdbcType=BIGINT}, #{name,jdbcType=VARCHAR}, ",
        "#{value,jdbcType=VARCHAR}, #{icon,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{uri,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsPermission record);

    @InsertProvider(type=UmsPermissionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsPermission record);

    @SelectProvider(type=UmsPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="pid", property="pid", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="uri", property="uri", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<UmsPermission> selectByExample(UmsPermissionExample example);

    @Select({
        "select",
        "id, pid, name, value, icon, type, uri, status, create_time, sort",
        "from ums_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="pid", property="pid", jdbcType=JdbcType.BIGINT),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="value", property="value", jdbcType=JdbcType.VARCHAR),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="uri", property="uri", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    UmsPermission selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsPermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example);

    @UpdateProvider(type=UmsPermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example);

    @UpdateProvider(type=UmsPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsPermission record);

    @Update({
        "update ums_permission",
        "set pid = #{pid,jdbcType=BIGINT},",
          "name = #{name,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "icon = #{icon,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "uri = #{uri,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsPermission record);
}