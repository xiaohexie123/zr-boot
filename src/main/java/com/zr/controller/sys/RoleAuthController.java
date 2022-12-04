package com.zr.controller.sys;

import com.alibaba.fastjson.JSON;
import com.zr.service.sys.RoleAuthService;
import com.zr.util.AjaxResult;
import com.zr.vo.sys.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sys/roleAuth")
public class RoleAuthController {

    @Autowired
    private RoleAuthService roleAuthService;


    @RequestMapping("/list")
    public String list(){
        //条件查询所有角色
        List<Auth> authList = roleAuthService.list();

        return JSON.toJSONString(AjaxResult.success("查询成功",authList));
    }
}
