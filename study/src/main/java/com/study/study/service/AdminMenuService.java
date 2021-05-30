package com.study.study.service;

import com.study.study.model.front.AdminMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AdminMenuService {

    public List<AdminMenu> getAdminMenu(){

        return Collections.singletonList(
                AdminMenu.builder().title("고객 관리").url("/pages/user").code("user").build()
        );

    }

}
