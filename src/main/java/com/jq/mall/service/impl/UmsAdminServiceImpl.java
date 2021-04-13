package com.jq.mall.service.impl;

import com.jq.mall.dto.AdminUserDetails;
import com.jq.mall.common.exception.Asserts;
import com.jq.mall.common.utils.JwtTokenUtil;
import com.jq.mall.dao.UmsAdminRoleRelationDao;
import com.jq.mall.dto.UmsAdminParam;
import com.jq.mall.dto.UpdateAdminPasswordParam;
import com.jq.mall.mbg.mapper.UmsAdminMapper;
import com.jq.mall.mbg.mapper.UmsAdminRoleRelationMapper;
import com.jq.mall.mbg.model.*;
import com.jq.mall.service.UmsAdminCacheService;
import com.jq.mall.service.UmsAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /**
     * 根据用户名获取后台管理员
     */
    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);  // Get redis
        if (admin != null) {
            return admin;
        }
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);  // where...
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            // Set redis
            adminCacheService.setAdmin(admin);
        }
        return admin;
    }

    /**
     * 根据用户id获取用户
     */
    @Override
    public UmsAdmin getAdminById(Long id) {
//        log.info("Service querying id: " + id);
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 注册功能
     */
    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);

        // 查询是否有相同用户名的用户
        if (getAdminByUsername(admin.getUsername()) != null) {
            return null;
        }

        // 将密码进行加密操作
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminMapper.insert(admin);

        return admin;
    }

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        try {
            UserDetails userDetails = getUserDetailsByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                Asserts.fail("用户名或密码错误");
            }
            if (!userDetails.isEnabled()) {
                Asserts.fail("账号已被禁用");
            }
            // Token
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            log.info("Token: {}", token);
        } catch (Exception e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     */
    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    /**
     * 修改指定用户信息
     *
     * @return count
     */
    @Override
    public int update(Long id, UmsAdmin admin) {
        return 0;
    }

    /**
     * 删除指定用户
     */
    @Override
    public int delete(Long id) {
        return 0;
    }

    /**
     * 修改密码
     */
    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        return 0;
    }

    /**
     * 获取用户信息
     */
    @Override
    public UserDetails getUserDetailsByUsername(String username) {
        UmsAdmin admin = getAdminByUsername(username);
        if (admin != null) {
            List<UmsPermission> permissionList = getPermissionList(admin.getId());
            return new AdminUserDetails(admin, permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 刷新token的功能
     * @param oldToken 旧Token
     */
    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshToken(oldToken);
    }

    /**
     * 获取指定用户的权限
     */
    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    /**
     * 获取指定用户的角色
     */
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    /**
     * 修改用户角色关系
     */
    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int roleCount = roleIds == null ? 0 : roleIds.size();
        // 先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        // 建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> adminRoleRelations = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation adminRoleRelation = new UmsAdminRoleRelation();
                adminRoleRelation.setAdminId(adminId);
                adminRoleRelation.setRoleId(roleId);
                adminRoleRelations.add(adminRoleRelation);
            }
            adminRoleRelationDao.insertAdminRoleRelations(adminRoleRelations);
        }
        return roleCount;
    }
}
