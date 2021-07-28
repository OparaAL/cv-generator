package com.application.cvgenerator.service.interfaces.user;

import com.application.cvgenerator.dto.user.ExperienceDto;
import com.application.cvgenerator.model.user.ExperienceEntity;
import com.application.cvgenerator.model.user.UserDataEntity;

import java.util.List;

public interface ExperienceService {
    List<ExperienceEntity> createExperiences(List<ExperienceDto> experiences, UserDataEntity userData);
    List<ExperienceEntity> updateExperiences(List<ExperienceDto> experiences, UserDataEntity userData);
}
