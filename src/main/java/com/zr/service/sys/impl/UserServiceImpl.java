package com.zr.service.sys.impl;

import com.zr.mapper.sys.UserMapper;
import com.zr.service.sys.UserService;
import com.zr.util.Md5Util;
import com.zr.util.RedisUtils;
import com.zr.util.TokenUtil;
import com.zr.vo.sys.User;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户业务接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String HEAD_FILE_DIR = "C:\\2022-ZR\\img\\head\\";

    @Resource
    private UserMapper userMapper;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 登录逻辑
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        //加密明文密码
        user.setPassword(Md5Util.EncoderByMd5(user.getPassword()));
        return userMapper.login(user);
    }

    /**
     * 查询列表
     *
     * @param user
     * @return
     */
    @Override
    public List<User> list(User user) {
        //查询开启分页，分页的pageNum和pageSize在BaseVo中
        PageHelper.startPage(user.getPageNum(), user.getPageSize());
        return userMapper.list(user);
    }

    /**
     * 更改用户状态
     *
     * @param user
     */
    @Override
    public void change(User user) {
        //获取原对象的userId和status属性，封装到新的对象中
        User u = new User();
        u.setUserId(user.getUserId());
        u.setStatus(user.getStatus());
        u.setModifyTime(new Date());
        u.setModifyId(tokenUtil.getUserId());
        //只更新有数据的列
        userMapper.updateByPrimaryKeySelective(u);
    }

    /**
     * 新增
     *
     * @param user
     */
    @Override
    public void add(User user) {
        //获取当前登录用户账号
        user.setCreateId(tokenUtil.getUserId());
        user.setCreateTime(new Date());
        //加密明文密码
        user.setPassword(Md5Util.EncoderByMd5(user.getPassword()));
        //默认为禁用状态
        user.setStatus("0");
        userMapper.insertSelective(user);
    }

    /**
     * 修改
     *
     * @param user
     */
    @Override
    public void edit(User user) {
        //如果存在密码，则修改密码
        if (!StringUtils.isNullOrEmpty(user.getPassword())) {
            user.setPassword(Md5Util.EncoderByMd5(user.getPassword()));
        }
        user.setModifyTime(new Date());
        //获取当前登录用户账号
        user.setModifyId(tokenUtil.getUserId());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 上传头像
     * @param file
     * @throws IOException
     */
    @Override
    public void uploadImg(MultipartFile file) throws IOException {
        String extensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String path = HEAD_FILE_DIR + UUID.randomUUID().toString() + extensionName;
        File imgFile = new File(path);
        file.transferTo(imgFile);
        User user = userMapper.selectByPrimaryKey(tokenUtil.getUserId());
        user.setImgUrl(path);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 获取用户头像的base64编码
     * @return String
     */
    @Override
    public String loadImg() {
        User user = userMapper.selectByPrimaryKey(tokenUtil.getUserId());
        return getBase64Code(user.getImgUrl());
    }

    /**
     * 根据用户账号获取头像的base64编码
     * @param userId
     * @return String
     */
    @Override
    public String loadImgByUserId(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return getBase64Code(user.getImgUrl());
    }

    /**
     * 退出登陆
     */
    @Override
    public void logout() {
        redisUtils.remove(tokenUtil.getToken());
    }

    /**
     * 根据账号获取权限列表
     * @param userId
     * @return
     */
    @Override
    public List<String> selectAuthUrlsList(String userId) {
        return userMapper.selectAuthUrlsList(userId);
    }

    /**
     * 根据文件地址生成base64编码
     * @param filePath
     * @return
     */
    private String getBase64Code(String filePath) {
        if(StringUtils.isNullOrEmpty(filePath)) return  "";
        byte[] b = new byte[0];
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            b = new byte[(int) file.length()];
            fis.read(b);
        } catch (Exception e) {
        }
        return Base64.getEncoder().encodeToString(b);
    }


    /**
     * 删除
     *
     * @param userId
     */
    @Override
    public void del(String userId) {
        userMapper.deleteByPrimaryKey(userId);
    }

}
