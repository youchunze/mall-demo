package com.histron.mall.dao;

import com.histron.model.entity.Role;
import com.histron.model.entity.User;
import com.histron.mall.MallWebApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@SpringBootTest(classes = MallWebApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired RoleDao roleDao;

    @Test
    public void save() {
        User  user = new User();
        user.setId("123111");
        user.setUsername("yyy");
        user.setMobile("1780498311");
        user.setPassword("123423");
        user.setCreateTime(new Date());
        user.setEnableState(1);

        Role role = new Role();
        role.setId("12121");
        role.setTenantId("1");
        role.setName("sada");
        role.setDescription("sdads");
        user.getRoles().add(role);
        role.getUsers().add(user);

        User user1 = userDao.save(user);

        System.out.println(user1);
    }

    }