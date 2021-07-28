package com.application.cvgenerator.service.implementation.user;

import com.application.cvgenerator.dto.user.ExperienceDto;
import com.application.cvgenerator.mapper.ExperienceMapper;
import com.application.cvgenerator.model.user.ExperienceEntity;
import com.application.cvgenerator.model.user.UserDataEntity;
import com.application.cvgenerator.repository.user.ExperienceRepository;
import com.application.cvgenerator.service.interfaces.user.ExperienceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    @Override
    @Transactional
    public List<ExperienceEntity> createExperiences(List<ExperienceDto> experiences, UserDataEntity userData) {
        List<ExperienceEntity> experiencesEntity = experienceMapper.experienceDtoListToEntityList(experiences);
        experiencesEntity.forEach(exp -> {
            exp.setUserData(userData);
            experienceRepository.save(exp);
        });
        return experiencesEntity;
    }

    @Override
    @Transactional
    public List<ExperienceEntity> updateExperiences(List<ExperienceDto> experiences, UserDataEntity userData) {
        List<ExperienceEntity> experienceEntities = new ArrayList<>();
        experienceRepository.deleteAllByUserData(userData);
        experiences.forEach(exp ->{
            /*if(exp.getId() != null) {
                Optional<ExperienceEntity> findExperience = experienceRepository.findById(exp.getId());
                findExperience.ifPresent(findExp -> {
                    if(!findExperience.get().getUserData().equals(userData)) throw new WrongUserDataException("Wrong user data");
                    ExperienceEntity experience = experienceMapper.experienceDtoToEntity(exp);
                    experience.setId(findExp.getId());
                    experience.setUserData(userData);
                    experienceRepository.save(experience);
                    experienceEntities.add(experience);
                });
            } else {
                ExperienceEntity experience = experienceMapper.experienceDtoToEntity(exp);
                experience.setUserData(userData);
                experienceRepository.save(experience);
                experienceEntities.add(experience);
            }*/
            ExperienceEntity experience = experienceMapper.experienceDtoToEntity(exp);
            experience.setUserData(userData);
            experienceRepository.save(experience);
            experienceEntities.add(experience);
        });

        return experienceEntities;
    }
}
