package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.header.Header;
import com.study.study.model.request.UserApiRequest;
import com.study.study.model.response.UserApiResponse;
import com.study.study.service.UserLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class CrudController<Res, Req, Entity> implements Crud<Res, Req> {

    @Autowired
    protected BaseService<Res, Req, Entity> baseService;

    @PostMapping("")
    public Header<Res> create(@RequestBody Header<Req> request) {
        return baseService.create(request);
    }

    @PutMapping("")
    public Header<Res> update(@RequestBody Header<Req> request) {
        return baseService.update(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<Res> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
