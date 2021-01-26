package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import jdk.javadoc.internal.doclets.formats.html.markup.Head;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {
    //repo import
    @Autowired
    private UserRepository userRepository;

    //1. request Data
    //2. user 생성
    //3. 생성된 데이터 -> UserApiResponse return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        //1. request Data
        UserApiRequest userApiRequest = request.getData();
        //2. user 생성
        User user = User.builder().account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED").phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail()).registeredAt(LocalDateTime.now()).build();

        User newUser = userRepository.save(user);

        // 3. 생성된 데이터 return userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //1. id > repository getOne, getById && 2. user > userApiResponse return
        return userRepository.findById(id)
                .map(user -> response(user))
                    .orElseGet(() -> Header.ERROR("데아터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data
        UserApiRequest userApiRequest = request.getData();

        //2. id > user 데이터 찾기

        //3. update

        //4. userApiResponse
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    // response
    private Header<UserApiResponse> response(User user) {
        //user > userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount()).password(user.getPassword()) //todo 암호화, 길이
                .email(user.getEmail()).phoneNumber(user.getPhoneNumber())
                .status(user.getStatus()).registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt()).build();

        //Header + data return
        return Header.OK(userApiResponse);
    }
}
