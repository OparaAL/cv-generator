package com.application.cvgenerator.mapper;

import com.application.cvgenerator.dto.user.ExperienceDto;
import com.application.cvgenerator.model.user.ExperienceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    ExperienceDto experienceToDto(ExperienceEntity experienceEntity);

    ExperienceEntity experienceDtoToEntity(ExperienceDto experienceDto);

    List<ExperienceEntity> experienceDtoListToEntityList(List<ExperienceDto> experienceDto);
}
