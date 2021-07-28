package com.application.cvgenerator.repository.user;

import com.application.cvgenerator.model.user.UserDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserDataEntity, Long> {
}
