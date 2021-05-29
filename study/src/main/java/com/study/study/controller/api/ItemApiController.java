package com.study.study.controller.api;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.Item;
import com.study.study.model.header.Header;
import com.study.study.model.request.ItemApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.service.ItemLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
@Slf4j
public class ItemApiController extends CrudController<ItemApiResponse, ItemApiRequest, Item> {

}
