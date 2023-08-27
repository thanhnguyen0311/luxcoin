package com.thanhnguyen.luxcoin.model.mapper;

import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserEditRequestDto;
import com.thanhnguyen.luxcoin.model.dto.userdto.response.UserEditRespone;
import com.thanhnguyen.luxcoin.model.dto.userdto.response.UserInfoResponse;
import com.thanhnguyen.luxcoin.model.entity.User;
import com.thanhnguyen.luxcoin.model.dto.userdto.request.UserRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {
    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    @Mapping(target = "name", source="UserEditRequestDto.name")
    @Mapping(target = "email", source="UserEditRequestDto.email")
    @Mapping(target = "phone", source="UserEditRequestDto.phone")
    @Mapping(target = "description", source="UserEditRequestDto.description")
    @Mapping(target = "image", source="UserEditRequestDto.image")
    User editRequestToEntity(UserEditRequestDto UserEditRequestDto);
    void editRequestToEntity(UserEditRequestDto userEditRequestDto, @MappingTarget User user);
    @Mapping(target = "name", source="userRegisterDto.name")
    @Mapping(target = "username", source="userRegisterDto.username")
    @Mapping(target = "password", source="userRegisterDto.password")
    @Mapping(target = "email", source="userRegisterDto.email")
    @Mapping(target = "date", source="userRegisterDto.date")
    @Mapping(target = "lastSeen", source="userRegisterDto.lastSeen")
    @Mapping(target = "isDeleted", source="userRegisterDto.isDeleted")
    @Mapping(target = "isActive", source="userRegisterDto.isActive")
    @Mapping(target = "role", source="userRegisterDto.role")
    User userRegisterDtoToEntity(UserRegisterDto userRegisterDto);
    @Mapping(target = "id", source="user.id")
    @Mapping(target = "name", source="user.name")
    @Mapping(target = "role", source="user.role.title")
    @Mapping(target = "image", source="user.image")
    UserInfoResponse userInfoResponse(User user);

    @Mapping(target = "name", source="user.name")
    @Mapping(target = "username", source="user.username")
    @Mapping(target = "email", source="user.email")
    @Mapping(target = "role", source="user.role")
    @Mapping(target = "phone", source="user.phone")
    @Mapping(target = "date", source="user.date")
    @Mapping(target = "description", source="user.description")
    @Mapping(target = "lastSeen", source="user.lastSeen")
    @Mapping(target = "isActive", source="user.isActive")
    @Mapping(target = "image", source="user.image")
    UserEditRespone userEditResponse(User user);
}
