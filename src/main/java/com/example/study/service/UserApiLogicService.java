package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {
    //repo import
    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;
    @Autowired
    private ItemApiLogicService itemApiLogicService;

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
                .status(UserStatus.REGISTERED).phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail()).registeredAt(LocalDateTime.now()).build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 return userApiResponse return
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        //1. id > repository getOne, getById && 2. user > userApiResponse return
        return baseRepository.findById(id)
                .map(user -> response(user))
                .map(Header::OK)
                    .orElseGet(() -> Header.ERROR("데아터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        //1. data
        UserApiRequest userApiRequest = request.getData();

        //2. id > user 데이터 찾기
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            //3. data -> update
            // 해당 id
            user.setAccount(userApiRequest.getAccount()).setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus()).setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail()).setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());

            return user;

        }) .map(user -> baseRepository.save(user)) //update -> newUser
                .map(updateUser -> response(updateUser)) //userApiResponse 생성
                .map(Header::OK) // Header + userapiresponse 에서 header 빼기 (response)
                .orElseGet(() -> Header.ERROR("데이터 없음 "));
    }

    @Override
    public Header delete(Long id) {
        // id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // repository -> delete   && response return
        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    // response
    private UserApiResponse response(User user) {
        //user > userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount()).password(user.getPassword()) //todo 암호화, 길이
                .email(user.getEmail()).phoneNumber(user.getPhoneNumber())
                .status(user.getStatus()).registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt()).build();

        //Header + data return : Header.OK(userApiResponse)
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search (Pageable pageable){
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());
        // List<UserApiResponse>
        // Header<List<UserApiResponse>>

        // 암호화 해야할 객체들을 위해 필요한 정보들 만
        // pagination으로 따로 만들어서 전달
        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();
        return Header.OK(userApiResponseList);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        //usert
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        //orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
                    //item api response
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;

                })
                .collect(Collectors.toList());
        userApiResponse.setOrderGroupApiResponseList((orderGroupApiResponseList));

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);

    }
}
