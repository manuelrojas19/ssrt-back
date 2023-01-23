package com.ipn.upiicsa.proy.sstr.userservice.mapper;

import com.ipn.upiicsa.proy.sstr.userservice.dto.SocialServiceDto;
import com.ipn.upiicsa.proy.sstr.userservice.entity.SocialService;
import org.mapstruct.Mapper;

@Mapper
public interface SocialServiceMapper {
    SocialServiceDto toDto(SocialService socialService);
    SocialService toEntity(SocialServiceDto serviceDto);
}
