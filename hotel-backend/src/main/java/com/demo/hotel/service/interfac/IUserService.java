package com.demo.hotel.service.interfac;


import com.demo.hotel.entity.User;
import com.demo.hotel.model.LoginRequest;
import com.demo.hotel.model.Response;

public interface IUserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response getUserBookingHistory(String userId);

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

}
