package com.jsonexercise.services.user;

import com.jsonexercise.domain.dto.exportDto.SellerDto;
import com.jsonexercise.domain.dto.exportDto.UserDto;
import com.jsonexercise.domain.dto.importDto.UserJsonDto;

import java.util.List;

public interface UserService {

    void persistUsers(UserJsonDto[] userDtos);

    void addFriends();

    List<SellerDto> findAllWithSoldProducts();

    List<UserDto> findAllWithAtLeastOneSoldProduct();
}