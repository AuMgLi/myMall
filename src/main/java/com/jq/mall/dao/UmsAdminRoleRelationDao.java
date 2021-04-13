package com.jq.mall.dao;

import com.jq.mall.mbg.model.UmsAdminRoleRelation;
import com.jq.mall.mbg.model.UmsPermission;
import com.jq.mall.mbg.model.UmsRole;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * ums_role:
 * '1', '商品管理员', '只能查看及操作商品';
 * '2', '订单管理员', '只能查看及操作订单';
 * '5', '超级管理员', '拥有所有查看和操作功能.
 *
 * ums_admin_role_relation:
 * admin 1, 3, 4是role 5，admin 6, 7分别是role 1, 2.
 *
 * ums_admin_permission_relation: 后台用户和权限关系表(除角色中定义的权限以外的加减权限)
 * type: 1: +; -1: -.
 */
public interface UmsAdminRoleRelationDao {

    @Select({
        "SELECT p.* FROM ums_admin_role_relation ar " +
            "LEFT JOIN ums_role r ON ar.role_id = r.id " +
            "LEFT JOIN ums_role_permission_relation rp ON r.id = rp.role_id " +
            "LEFT JOIN ums_permission p ON rp.permission_id = p.id " +
        "WHERE ar.admin_id = #{adminId,jdbcType=BIGINT} " +
            "AND p.id IS NOT NULL " +
            "AND p.id NOT IN (" +
                "SELECT p.id FROM ums_admin_permission_relation ap " +
                    "LEFT JOIN ums_permission p ON ap.permission_id = p.id " +
                "WHERE ap.type = -1 AND ap.admin_id = #{adminId,jdbcType=BIGINT}" +
                ") " +
        "UNION " +
        "SELECT p.* FROM ums_admin_permission_relation ap " +
            "LEFT JOIN ums_permission p ON ap.permission_id = p.id " +
        "WHERE ap.type = 1 AND ap.admin_id = #{adminId,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, id=true),
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
    List<UmsPermission> getPermissionList(Long adminId);

    @Select({
        "SELECT r.* FROM ums_admin_role_relation ar " +
        "LEFT JOIN ums_role r ON ar.role_id = r.id " +
        "WHERE ar.admin_id = #{adminId,jdbcType=BIGINT}"
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
    List<UmsRole> getRoleList(Long adminId);

    @Insert({
        "<script>INSERT INTO ums_admin_role_relation (admin_id, role_id) VALUES" +
        "<foreach collection='list' separator=',' item='item' index='index'>" +
            "(#{item.adminId,jdbcType=BIGINT}, #{item.roleId,jdbcType=BIGINT})" +
        "</foreach></script>"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertAdminRoleRelations(@Param("list") List<UmsAdminRoleRelation> adminRoleRelations);
}
