package com.zr.controller.sys;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zr.service.sys.RoleService;
import com.zr.util.AjaxResult;
import com.zr.vo.sys.Role;
import com.zr.vo.sys.User;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys/role")
public class RoleController {


    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String list(@RequestBody Role role){
        //条件查询所有角色
        List<Role> roleList = roleService.list(role);
        //封装到分页对象中
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        return JSON.toJSONString(AjaxResult.success("查询成功",pageInfo));
    }

    @RequestMapping("/add")
    public String add(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        Role role = JSON.parseObject(JSON.toJSONString(dataMap), Role.class);
         roleService.add(role);
        return JSON.toJSONString(AjaxResult.success("新增成功"));
    }

    @RequestMapping("/edit")
    public String edit(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        Role role = JSON.parseObject(JSON.toJSONString(dataMap), Role.class);
        roleService.edit(role);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    @RequestMapping("/del/{id}")
    public String del(@PathVariable Long id) {
        roleService.del(id);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }
}
