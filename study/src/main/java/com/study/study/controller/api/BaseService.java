package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.header.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
public abstract class BaseService<Res, Req, Entity> implements Crud<Res, Req> {

    @Autowired(required = false)
    protected JpaRepository<Entity, Long> baseRepository;

}
