package com.zqb.web.Controller;


import com.zqb.system.domain.User;
import com.zqb.system.service.IUserService;
import com.zqb.system.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Controller
public class TestController {

    @Autowired
    DataSource dataSource;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping("/test")
    public void test1() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("=11=" + connection);
    }

    @RequestMapping("/test1")
    @ResponseBody
    public List<User> test2() {
        List<User> users = userService.selectList(null);
        return users;
    }
}
