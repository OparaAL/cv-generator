package com.application.cvgenerator.mapper;

import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.model.user.UserDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExperienceMapper.class})
public interface UserDataMapper {

    UserDataEntity userDataDtoToEntity(UserDataDto userDataDto);

    @Mapping(source = "experiences", target = "experiences")
    UserDataDto entityToUserDataDto(UserDataEntity userDataEntity);
}
