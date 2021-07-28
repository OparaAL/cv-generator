package com.application.cvgenerator.repository.cv;

import com.application.cvgenerator.model.cv.CvEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CvRepository extends JpaRepository<CvEntity, Long> {
}
