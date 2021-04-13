package com.jq.mall.mbg.mapper;

import com.jq.mall.mbg.model.UmsAdminRoleRelation;
import com.jq.mall.mbg.model.UmsAdminRoleRelationExample;
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

public interface UmsAdminRoleRelationMapper {
    @SelectProvider(type=UmsAdminRoleRelationSqlProvider.class, method="countByExample")
    long countByExample(UmsAdminRoleRelationExample example);

    @DeleteProvider(type=UmsAdminRoleRelationSqlProvider.class, method="deleteByExample")
    int deleteByExample(UmsAdminRoleRelationExample example);

    @Delete({
        "delete from ums_admin_role_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into ums_admin_role_relation (admin_id, role_id)",
        "values (#{adminId,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UmsAdminRoleRelation record);

    @InsertProvider(type=UmsAdminRoleRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insertSelective(UmsAdminRoleRelation record);

    @SelectProvider(type=UmsAdminRoleRelationSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    @Select({
        "select",
        "id, admin_id, role_id",
        "from ums_admin_role_relation",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.BIGINT),
        @Result(column="role_id", property="roleId", jdbcType=JdbcType.BIGINT)
    })
    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    @UpdateProvider(type=UmsAdminRoleRelationSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);

    @UpdateProvider(type=UmsAdminRoleRelationSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);

    @UpdateProvider(type=UmsAdminRoleRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UmsAdminRoleRelation record);

    @Update({
        "update ums_admin_role_relation",
        "set admin_id = #{adminId,jdbcType=BIGINT},",
          "role_id = #{roleId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UmsAdminRoleRelation record);
}