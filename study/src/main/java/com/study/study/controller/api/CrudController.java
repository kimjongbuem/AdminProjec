package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.header.Header;
import com.study.study.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
public abstract class CrudController<Res, Req, Entity> implements Crud<Res, Req> {

    @Autowired
    protected BaseService<Res, Req, Entity> baseService;

    @GetMapping("")
    public Header<List<Res>> getPageList(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 10) Pageable pageable){
        return baseService.getPageList(pageable);
    }

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
