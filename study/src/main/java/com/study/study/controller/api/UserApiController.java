package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.header.Header;
import com.study.study.model.request.UserApiRequest;
import com.study.study.model.response.UserApiResponse;
import com.study.study.model.service.UserLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements Crud<UserApiResponse, UserApiRequest> {

    @Autowired
    private UserLogicService userLogicService;

    @Override
    @PostMapping("")
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userLogicService.create(request);
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        log.info("{}", request);
        return userLogicService.update(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<UserApiResponse> read(@PathVariable Long id) {
        log.info("read : id {}", id);
        return userLogicService.read(id);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return userLogicService.delete(id);
    }
}
