package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.OrderGroup;
import com.study.study.model.header.Header;
import com.study.study.model.request.OrderGroupApiRequest;
import com.study.study.model.response.OrderGroupApiResponse;
import com.study.study.service.OrderGroupLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiResponse, OrderGroupApiRequest, OrderGroup> {

}
