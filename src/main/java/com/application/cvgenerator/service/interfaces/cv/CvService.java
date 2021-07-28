package com.application.cvgenerator.service.interfaces.cv;

import com.application.cvgenerator.dto.cv.CvDto;
import com.application.cvgenerator.dto.user.UserDataDto;

import java.io.IOException;

public interface CvService {
    void downloadCv(Long cvId, String path) throws IOException;
    CvDto createCv(UserDataDto userDataDto) throws IOException;
    CvDto updateCv(UserDataDto userDataDto, Long cvId) throws IOException;
    void delete(Long cvId);
}
