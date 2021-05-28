package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.Item;
import com.study.study.model.entity.OrderGroup;
import com.study.study.model.header.Header;
import com.study.study.model.request.OrderGroupApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.model.response.OrderGroupApiResponse;
import com.study.study.model.service.OrderGroupLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController implements Crud<OrderGroupApiResponse, OrderGroupApiRequest> {

    @Autowired
    private OrderGroupLogicService orderGroupLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupLogicService.create(request);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> request) {
        return orderGroupLogicService.update(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        return orderGroupLogicService.read(id);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return orderGroupLogicService.delete(id);
    }
}
