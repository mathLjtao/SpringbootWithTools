package com.ljtao.springbootpagehelper.dao;

import com.ljtao.springbootpagehelper.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ljtao3 on 2020/4/26
 */
public interface UserDao {
    @Select("select user_id id,user_name userName from sys_user")
    List<User> selectAll();
}
