package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.User;
import com.study.study.model.header.Header;
import com.study.study.model.request.UserApiRequest;
import com.study.study.model.response.UserApiResponse;
import com.study.study.model.response.UserOrderInfoApiResponse;
import com.study.study.service.UserLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiResponse, UserApiRequest, User>{

    @Autowired
    private UserLogicService userLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userLogicService.orderInfo(id);
    }
}
