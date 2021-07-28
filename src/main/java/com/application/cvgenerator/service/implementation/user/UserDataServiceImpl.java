package com.application.cvgenerator.service.implementation.user;

import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.exception.ItemNotFoundException;
import com.application.cvgenerator.mapper.UserDataMapper;
import com.application.cvgenerator.model.user.UserDataEntity;
import com.application.cvgenerator.repository.user.ExperienceRepository;
import com.application.cvgenerator.repository.user.UserDataRepository;
import com.application.cvgenerator.service.interfaces.user.ExperienceService;
import com.application.cvgenerator.service.interfaces.user.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {

    private final UserDataMapper userDataMapper;
    private final ExperienceRepository experienceRepository;
    private final UserDataRepository userDataRepository;
    private final ExperienceService experienceService;

    @Override
    @Transactional
    public UserDataEntity createUserData(UserDataDto userDataDto) {
        UserDataEntity userData = userDataMapper.userDataDtoToEntity(userDataDto);
        userData.setExperiences(experienceService.createExperiences(userDataDto.getExperiences(), userData));
        userDataRepository.save(userData);
        return userData;
    }

    @Override
    @Transactional
    public UserDataEntity updateUserData(UserDataDto userDataDto, Long userDataId) {

        UserDataEntity findUserData = userDataRepository.findById(userDataId)
                .orElseThrow(() -> new ItemNotFoundException("User data not found"));
        UserDataEntity userData = userDataMapper.userDataDtoToEntity(userDataDto);

        if (!userDataDto.getExperiences().isEmpty())
            userData.setExperiences(experienceService.updateExperiences(userDataDto.getExperiences(), findUserData));

        userData.setId(findUserData.getId());
        userDataRepository.save(userData);

        return userData;
    }

    @Override
    @Transactional
    public void deleteUserData(UserDataEntity userData) {
        experienceRepository.deleteAllByUserData(userData);
        userDataRepository.delete(userData);
    }


}
