package com.jq.mall.controller;

import com.jq.mall.common.CommonResult;
import com.jq.mall.dto.UmsAdminLoginParam;
import com.jq.mall.dto.UmsAdminParam;
import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.mbg.model.UmsRole;
import com.jq.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "UmsAdminController", description = "用户管理")
@Controller
@RequestMapping("/admin")
@Slf4j
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @ApiOperation(value = "管理员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam adminParam) {
        UmsAdmin admin = adminService.register(adminParam);
        if (admin == null) {
            return CommonResult.failed("用户名已存在，请更改");
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "登录并返回Token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam adminLoginParam) {
        log.debug("Log debug");
        String token = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);

        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "根据ID获取管理员信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getAdminById(@PathVariable Long id) {
        log.info("Querying id: " + id);
        UmsAdmin admin = adminService.getAdminById(id);
        if (admin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "刷新Token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Object> refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshedToken = adminService.refreshToken(token);
        if (refreshedToken == null) {
            return CommonResult.failed("Token已过期，刷新失败，请重新登录");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshedToken);
        tokenMap.put("tokenHead", tokenHead);

        return CommonResult.success(tokenMap);
    }

    @ApiOperation("给用户分配角色: '1', '商品管理员', '只能查看及操作商品'; " +
            "'2', '订单管理员', '只能查看及操作订单'; " +
            "'5', '超级管理员', '拥有所有查看和操作功能.")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> updateRole(@RequestParam("adminId") Long adminId,
                                           @RequestParam("roleIds") List<Long> roleIds) {
        int roleCount = adminService.updateRole(adminId, roleIds);
        if (roleCount >= 0) {
            return CommonResult.success("Update admin " + adminId + " succeed with " + roleCount + " roles");
        }
        return CommonResult.failed("Update admin " + adminId + "'s role failed");
    }

    @ApiOperation("获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }
}
