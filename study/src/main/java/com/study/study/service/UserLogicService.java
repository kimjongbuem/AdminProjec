package com.study.study.service;

import com.study.study.controller.api.BaseService;
import com.study.study.ifs.Crud;
import com.study.study.model.entity.OrderDetail;
import com.study.study.model.entity.OrderGroup;
import com.study.study.model.entity.User;
import com.study.study.model.enumclass.UserStatus;
import com.study.study.model.header.Header;
import com.study.study.model.header.PagingNation;
import com.study.study.model.request.UserApiRequest;
import com.study.study.model.response.ItemApiResponse;
import com.study.study.model.response.OrderGroupApiResponse;
import com.study.study.model.response.UserApiResponse;
import com.study.study.model.response.UserOrderInfoApiResponse;
import com.study.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserLogicService extends BaseService<UserApiResponse, UserApiRequest, User> {

    @Autowired
    private OrderGroupLogicService orderGroupLogicService;

    @Autowired
    private ItemLogicService itemLogicService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        UserApiRequest data = request.getData();
        User user = User.builder().account(data.getAccount())
                                     .email(data.getEmail())
                                     .phoneNumber(data.getPhoneNumber())
                                     .password(data.getPassword())
                                     .status(UserStatus.REGISTERED)
                                     .registeredAt(LocalDateTime.now())
                                     .build();

        User newUser = baseRepository.save(user);

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest data = request.getData();

        return baseRepository.findById(data.getId()).map(
                user ->
                {
                        user.setAccount(data.getAccount());
                        user.setEmail(data.getEmail());
                        user.setPassword(data.getPassword());
                        user.setPhoneNumber(data.getPhoneNumber());
                        user.setStatus(data.getStatus());
                        user.setRegisteredAt(user.getRegisteredAt());
                        user.setUnregisteredAt(user.getUnregisteredAt());
                        return user;
                }
        ).map(
                user-> baseRepository.save(user)
        ).map(
              user -> Header.OK(response(user))
        ).orElseGet(()->Header.ERROR("데이터없음"));

    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id).map(
                user -> Header.OK(response(user)))
                .orElseGet(()-> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id).map(
                user ->
                {
                    user.setDeleted(true);
                    return user;
                }
        ).map(
                user-> baseRepository.save(user)
        ).map(
                user -> Header.OK(response(user))
        ).orElseGet(()->Header.ERROR("데이터없음"));
    }

    private UserApiResponse response(User user){

        return UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id){
        User user = baseRepository.getOne(id);

        UserApiResponse userApiResponse = response(user);

        List<OrderGroup> orderGroupList = user.getOrderGroupList();

        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(
                        orderGroup ->  {
                            OrderGroupApiResponse orderGroupApiResponse = orderGroupLogicService.response(orderGroup).getData();
                            List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                                    .map(OrderDetail::getItem)
                                    .map(item -> itemLogicService.response(item).getData())
                                    .collect(Collectors.toList());

                            orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                            return orderGroupApiResponse;
                        }
                ).collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }

    @Override
    public Header<List<UserApiResponse>> getPageList(Pageable pageable) {
        Page<User> all = baseRepository.findAll(pageable);
        List<UserApiResponse> apiResponseList = all.stream()
                .map(
                        this::response
                ).collect(Collectors.toList());

        PagingNation pagingNation = PagingNation.builder()
                .totalPages(all.getTotalPages())
                .totalElements(all.getTotalElements())
                .currentPage(all.getNumber())
                .currentElements(all.getNumberOfElements())
                .build();

        return Header.OK(apiResponseList, pagingNation);
    }
}
