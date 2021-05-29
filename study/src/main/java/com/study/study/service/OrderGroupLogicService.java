package com.study.study.service;

import com.study.study.controller.api.BaseService;
import com.study.study.model.entity.OrderGroup;
import com.study.study.model.header.Header;
import com.study.study.model.request.OrderGroupApiRequest;
import com.study.study.model.response.OrderGroupApiResponse;
import com.study.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderGroupLogicService extends BaseService<OrderGroupApiResponse, OrderGroupApiRequest, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest data = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(data.getStatus())
                .orderType(data.getOrderType())
                .revAddress(data.getRevAddress())
                .revName(data.getRevName())
                .paymentType(data.getPaymentType())
                .totalPrice(data.getTotalPrice())
                .totalQuantity(data.getTotalQuantity())
                .orderAt(data.getOrderAt())
                .arrivalDate(data.getArrivalDate())
                .user(userRepository.getOne(data.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest data = request.getData();

        return baseRepository.findById(data.getId()).
                map(orderGroup -> {
                    orderGroup.setStatus(data.getStatus());
                    orderGroup.setOrderType(data.getOrderType());
                    orderGroup.setRevAddress(data.getRevAddress());
                    orderGroup.setRevName(data.getRevName());
                    orderGroup.setPaymentType(data.getPaymentType());
                    orderGroup.setTotalPrice(data.getTotalPrice());
                    orderGroup.setTotalQuantity(data.getTotalQuantity());
                    orderGroup.setOrderAt(data.getOrderAt());
                    return orderGroup;
                }).map(
                orderGroup-> baseRepository.save(orderGroup)
                ).map(
                    this::response
                ).
                orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return baseRepository.findById(id).
                map(this::response).
                orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id).
                map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                }).
                orElseGet(() -> Header.ERROR("데이터없음"));
    }

    private Header<OrderGroupApiResponse> response(OrderGroup data){
        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .status(data.getStatus())
                .orderType(data.getOrderType())
                .revAddress(data.getRevAddress())
                .arrivalDate(data.getArrivalDate())
                .revName(data.getRevName())
                .paymentType(data.getPaymentType())
                .totalPrice(data.getTotalPrice())
                .totalQuantity(data.getTotalQuantity())
                .orderAt(data.getOrderAt())
                .arrivalDate(data.getArrivalDate())
                .userId(data.getUser().getId())
                .id(data.getId())
                .build();

        return Header.OK(orderGroupApiResponse);
    }
}
