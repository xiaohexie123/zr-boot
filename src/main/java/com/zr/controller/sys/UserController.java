package com.zr.controller.sys;

import com.alibaba.fastjson.JSON;
import com.zr.service.sys.UserService;
import com.zr.util.AjaxResult;
import com.zr.util.RedisUtils;
import com.zr.vo.sys.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.List;

/**
 * 用户操作类
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 用户登录
     * @param user
     * @return String
     */
    @PostMapping("/login")
    public String login(@RequestBody User user){
        User loginUser = userService.login(user);
        AjaxResult ajaxResult = null;
        if (loginUser != null) {
            //根据登录账号获取对应的菜单权限
            List<String> authUrlsList = userService.selectAuthUrlsList(loginUser.getUserId());
            //生成token
            String token = UUID.randomUUID().toString();
            //把token放入redis缓存,有效时间为15分钟
            redisUtils.set(token, loginUser, 15L, TimeUnit.MINUTES);
            //返回结果给前台，token放入msg属性中，前台保存，以后每次请求需要携带token身份信息
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("loginUserName", loginUser.getUserName());
            dataMap.put("base64",userService.loadImgByUserId(loginUser.getUserId()));
            dataMap.put("authUrlsList",authUrlsList);
            ajaxResult = new AjaxResult(true, token, dataMap);
        } else {
            ajaxResult = new AjaxResult(false, "账号或密码错误");
        }
        return JSON.toJSONString(ajaxResult);
    }

    @RequestMapping("/logout")
    public String logout(){
        userService.logout();
        return JSON.toJSONString(AjaxResult.success("退出成功"));
    }

    /**
     * 查询用户列表
     * @param user
     * @return
     */
    @RequestMapping("/list")
    public String list(@RequestBody User user) {
        //查询用户列表
        List<User> userList = userService.list(user);
        //封装到分页对象中
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return JSON.toJSONString(AjaxResult.success("查询成功", pageInfo));
    }

    /**
     * 更改用户状态
     * @param dataMap
     * @return
     */
    @RequestMapping("/change")
    public String change(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        User user = JSON.parseObject(JSON.toJSONString(dataMap), User.class);
        userService.change(user);
        return JSON.toJSONString(AjaxResult.success("操作成功"));
    }

    /**
     * 新增方法
     * @param dataMap
     * @return
     */
    @RequestMapping("/add")
    public String add(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        User user = JSON.parseObject(JSON.toJSONString(dataMap), User.class);
        userService.add(user);
        return JSON.toJSONString(AjaxResult.success("新增成功"));
    }

    /**
     * 修改方法
     * @param dataMap
     * @return
     */
    @RequestMapping("/edit")
    public String edit(@RequestBody Map dataMap) {
        //转换请求的数据为对应的VO
        User user = JSON.parseObject(JSON.toJSONString(dataMap), User.class);
        userService.edit(user);
        return JSON.toJSONString(AjaxResult.success("修改成功"));
    }

    /**
     * 删除方法
     * @param userId
     * @return
     */
    @RequestMapping("/del/{userId}")
    public String del(@PathVariable  String userId) {
        userService.del(userId);
        return JSON.toJSONString(AjaxResult.success("删除成功"));
    }

    /**
     * 头像上传
     * @return String
     */
    @RequestMapping("/uploadImg")
    public String uploadImg(MultipartFile file) throws IOException {
        userService.uploadImg(file);
        String base64 = userService.loadImg();
        return JSON.toJSONString(AjaxResult.success("上传成功",base64));
    }

    /**
     * 头像下载
     * @return String
     */
    @RequestMapping("/loadImg")
    public String loadImg(String userId){
        String base64 = userService.loadImg();
        return JSON.toJSONString(AjaxResult.success("上传成功",base64));
    }

}
