package com.application.cvgenerator.repository.user;

import com.application.cvgenerator.model.user.ExperienceEntity;
import com.application.cvgenerator.model.user.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Long> {

    void deleteAllByUserData(UserDataEntity userData);
}
