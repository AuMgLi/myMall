package com.jq.mall.controller;

import com.jq.mall.common.CommonResult;
import com.jq.mall.dto.UmsAdminLoginParam;
import com.jq.mall.dto.UmsAdminParam;
import com.jq.mall.mbg.model.UmsAdmin;
import com.jq.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "UmsAdminController")
@Controller
@RequestMapping("/admin")
@Slf4j
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "管理员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam adminParam) {
        UmsAdmin admin = adminService.register(adminParam);
        if (admin == null) {
            return CommonResult.failed();
        }
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "登录并返回Token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<Map<String, String>> login(@Validated @RequestBody UmsAdminLoginParam adminLoginParam) {
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
}
