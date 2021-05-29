package com.study.study.controller.api;

import com.study.study.model.entity.Partner;
import com.study.study.model.request.PartnerApiRequest;
import com.study.study.model.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/partner")
public class PartnerController extends CrudController<PartnerApiResponse, PartnerApiRequest, Partner>{
}
