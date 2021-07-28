package com.application.cvgenerator.service.interfaces.user;

import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.model.user.UserDataEntity;

public interface UserDataService {
    UserDataEntity createUserData(UserDataDto userDataDto);
    UserDataEntity updateUserData(UserDataDto userDataDto, Long userDataId);
    void deleteUserData(UserDataEntity userData);
}
