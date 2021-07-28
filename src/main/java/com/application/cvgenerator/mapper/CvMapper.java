package com.application.cvgenerator.mapper;

import com.application.cvgenerator.dto.cv.CvDto;
import com.application.cvgenerator.model.cv.CvEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserDataMapper.class, ExperienceMapper.class})
public interface CvMapper {

    CvDto cvEntityToDto(CvEntity cvEntity);

    CvEntity cvDtoToEntity(CvDto cvDto);

}
