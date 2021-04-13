package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsAdminPermissionRelation;
import com.jq.mall.mbg.model.UmsAdminPermissionRelationExample;
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

public interface UmsAdminPermissionRelationMapper {
    @SelectProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="countByExample")
    long countByExample(UmsAdminPermissionRelationExample example);

    @DeleteProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsAdminPermissionRelationExample example);

    @Delete({
        "delete from ums_admin_permission_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_admin_permission_relation (admin_id, permission_id, ",
        "type)",
        "values (#{adminId,jdbcType=BIGINT}, #{permissionId,jdbcType=BIGINT}, ",
        "#{type,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsAdminPermissionRelation record);

    @InsertProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsAdminPermissionRelation record);

    @SelectProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    List<UmsAdminPermissionRelation> selectByExample(UmsAdminPermissionRelationExample example);

    @Select({
        "select",
        "id, admin_id, permission_id, type",
        "from ums_admin_permission_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER)
    })
    UmsAdminPermissionRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example);

    @UpdateProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example);

    @UpdateProvider(type=UmsAdminPermissionRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsAdminPermissionRelation record);

    @Update({
        "update ums_admin_permission_relation",
        "set admin_id = #{adminId,jdbcType=BIGINT},",
          "permission_id = #{permissionId,jdbcType=BIGINT},",
          "type = #{type,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsAdminPermissionRelation record);
}