package com.study.study.model.service;

import com.study.study.ifs.Crud;
import com.study.study.model.entity.User;
import com.study.study.model.enumclass.UserStatus;
import com.study.study.model.header.Header;
import com.study.study.model.request.UserApiRequest;
import com.study.study.model.response.UserApiResponse;
import com.study.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserLogicService implements Crud<UserApiResponse, UserApiRequest> {

    @Autowired
    private UserRepository userRepository;

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

        User newUser = userRepository.save(user);

        return response(newUser) ;
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest data = request.getData();

        return userRepository.findById(data.getId()).map(
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
                user-> userRepository.save(user)
        ).map(
                this::response
        ).orElseGet(()->Header.ERROR("데이터없음"));

    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id).map(
                this::response)
                .orElseGet(()-> Header.ERROR("데이터없음"));
    }

    @Override
    public Header delete(Long id) {

        return userRepository.findById(id).map(
                user ->
                {
                    user.setDeleted(true);
                    return user;
                }
        ).map(
                user-> userRepository.save(user)
        ).map(
                this::response
        ).orElseGet(()->Header.ERROR("데이터없음"));
    }

    private Header<UserApiResponse> response(User user){

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }

}
