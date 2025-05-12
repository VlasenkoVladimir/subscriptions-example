package com.vlasenko.subscriptions_example.mapper;

import com.vlasenko.subscriptions_example.domain.User;
import com.vlasenko.subscriptions_example.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * User mapper
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(UserDto userDto);

    UserDto toDto(User user);
}