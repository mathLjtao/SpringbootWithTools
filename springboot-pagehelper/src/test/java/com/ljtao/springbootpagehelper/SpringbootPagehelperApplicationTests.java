package com.ljtao.springbootpagehelper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ljtao.springbootpagehelper.dao.UserDao;
import com.ljtao.springbootpagehelper.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SpringbootPagehelperApplicationTests {
    @Autowired
    private UserDao userDao;

    /*

     */
    @Test
    void contextLoads() {
        List<String> strings = Arrays.asList("a", "s", "d","f","g");
        PageHelper.startPage(2,2);
        //只对查询数据库的数据有效果。
        PageInfo<User> pageInfo=new PageInfo<>(userDao.selectAll());
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList().get(0).getUserName());
    }


}
