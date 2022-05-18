package com.zr.controller.sys;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zr.service.sys.AuthService;
import com.zr.util.AjaxResult;
import com.zr.vo.sys.Auth;
import com.zr.vo.sys.Role;
import com.zr.vo.sys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping("/list")
    public String list(){
        //条件查询所有角色
        List<Auth> authList = authService.list();

        return JSON.toJSONString(AjaxResult.success("查询成功",authList));
    }

    @RequestMapping("/add")
    public String add(@RequestBody Auth auth){
        //条件查询所有角色
         authService.add(auth);

        return JSON.toJSONString(AjaxResult.success("查询成功"));
    }

    @RequestMapping("/edit")
    public String edit(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        Auth auth = JSON.parseObject(JSON.toJSONString(dataMap), Auth.class);
        authService.edit(auth);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable Long id) {
        authService.del(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

}
