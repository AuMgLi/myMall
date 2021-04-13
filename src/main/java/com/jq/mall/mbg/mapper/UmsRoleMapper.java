package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsRole;
import com.jq.mall.mbg.model.UmsRoleExample;
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

public interface UmsRoleMapper {
    @SelectProvider(type=UmsRoleSqlProvider.class, method="countByExample")
    long countByExample(UmsRoleExample example);

    @DeleteProvider(type=UmsRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsRoleExample example);

    @Delete({
        "delete from ums_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_role (name, description, ",
        "admin_count, create_time, ",
        "status, sort)",
        "values (#{name,jdbcType=VARCHAR}, #{description,jdbcType=VARCHAR}, ",
        "#{adminCount,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsRole record);

    @InsertProvider(type=UmsRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsRole record);

    @SelectProvider(type=UmsRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_count", property="adminCount", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    List<UmsRole> selectByExample(UmsRoleExample example);

    @Select({
        "select",
        "id, name, description, admin_count, create_time, status, sort",
        "from ums_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="admin_count", property="adminCount", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="sort", property="sort", jdbcType=JdbcType.INTEGER)
    })
    UmsRole selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    @UpdateProvider(type=UmsRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    @UpdateProvider(type=UmsRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsRole record);

    @Update({
        "update ums_role",
        "set name = #{name,jdbcType=VARCHAR},",
          "description = #{description,jdbcType=VARCHAR},",
          "admin_count = #{adminCount,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsRole record);
}