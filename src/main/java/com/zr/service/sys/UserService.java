package com.zr.service.sys;

import com.zr.vo.sys.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    User login(User user);

    List<User> list(User user);

    void change(User user);

    void del(String userId);

    void add(User user);

    void edit(User user);

    void uploadImg(MultipartFile file) throws IOException;

    String loadImg();

    String loadImgByUserId(String userId);

    void logout();

    List<String> selectAuthUrlsList(String userId);
}
