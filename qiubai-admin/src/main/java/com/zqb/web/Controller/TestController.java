package com.zqb.web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Controller
public class TestController {

    @Autowired
    DataSource dataSource;

    @RequestMapping("/test")
    public void test1() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("=="+connection);
    }
}
