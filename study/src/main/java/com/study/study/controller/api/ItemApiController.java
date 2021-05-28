package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.header.Header;
import com.study.study.model.request.ItemApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.model.service.ItemLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@Slf4j
public class ItemApiController implements Crud<ItemApiResponse, ItemApiRequest> {

    @Autowired
    private ItemLogicService itemLogicService;


    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
        log.info("{}", request);
        return itemLogicService.create(request);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
        return itemLogicService.update(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemLogicService.read(id);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return itemLogicService.delete(id);
    }
}
