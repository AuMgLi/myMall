package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsRolePermissionRelation;
import com.jq.mall.mbg.model.UmsRolePermissionRelationExample;
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

public interface UmsRolePermissionRelationMapper {
    @SelectProvider(type=UmsRolePermissionRelationSqlProvider.class, method="countByExample")
    long countByExample(UmsRolePermissionRelationExample example);

    @DeleteProvider(type=UmsRolePermissionRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsRolePermissionRelationExample example);

    @Delete({
        "delete from ums_role_permission_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_role_permission_relation (role_id, permission_id)",
        "values (#{roleId,jdbcType=BIGINT}, #{permissionId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsRolePermissionRelation record);

    @InsertProvider(type=UmsRolePermissionRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsRolePermissionRelation record);

    @SelectProvider(type=UmsRolePermissionRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsRolePermissionRelation> selectByExample(UmsRolePermissionRelationExample example);

    @Select({
        "select",
        "id, role_id, permission_id",
        "from ums_role_permission_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT),
        @Result(column="permission_id", property="permissionId", jdbcType=JdbcType.BIGINT)
    })
    UmsRolePermissionRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsRolePermissionRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example);

    @UpdateProvider(type=UmsRolePermissionRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example);

    @UpdateProvider(type=UmsRolePermissionRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsRolePermissionRelation record);

    @Update({
        "update ums_role_permission_relation",
        "set role_id = #{roleId,jdbcType=BIGINT},",
          "permission_id = #{permissionId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsRolePermissionRelation record);
}