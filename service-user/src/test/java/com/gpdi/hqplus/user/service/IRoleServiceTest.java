package com.gpdi.hqplus.user.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.gpdi.hqplus.common.util.IdGenerator;
import com.gpdi.hqplus.user.entity.Role;
import com.gpdi.hqplus.user.entity.UserRoleRel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IRoleServiceTest {

    @Autowired
    IRoleService roleService;
    @Autowired
    IdGenerator idGenerator;

    @Test
    public void listRole() {

        String[] roles = {"admin"};

        Role role = new Role();
        role.setId(idGenerator.getNumberId());
        role.setCode("admin");
        role.setName("管理员");
        role.setStatus(Role.STATUS_NORMAL);
        System.out.println(role.insert());

        UserRoleRel rel = new UserRoleRel();
        rel.setId(idGenerator.getNumberId());
        rel.setRoleCode(role.getCode());
        rel.setUserId(595215351470501888l);
        rel.setStatus(UserRoleRel.STATUS_NORMAL);
        System.out.println(rel.insert());
    }
}