package com.study.study.model.service;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.OrderGroup;
import com.study.study.model.header.Header;
import com.study.study.model.request.OrderGroupApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.model.response.OrderGroupApiResponse;
import com.study.study.repository.OrderGroupRepository;
import com.study.study.repository.UserRepository;
import org.aspectj.weaver.ast.Or;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;

@Service
public class OrderGroupLogicService implements Crud<OrderGroupApiResponse, OrderGroupApiRequest> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

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

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest data = request.getData();

        return orderGroupRepository.findById(data.getId()).
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
                orderGroup-> orderGroupRepository.save(orderGroup)
                ).map(
                    this::response
                ).
                orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository.findById(id).
                map(this::response).
                orElseGet(() -> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {
        return orderGroupRepository.findById(id).
                map(orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
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
