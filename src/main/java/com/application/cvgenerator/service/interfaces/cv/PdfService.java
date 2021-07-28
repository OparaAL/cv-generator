package com.application.cvgenerator.service.interfaces.cv;

import com.application.cvgenerator.dto.user.UserDataDto;

import java.io.IOException;
import java.util.Map;

public interface PdfService {
    Map<String, byte[]> createPdf(UserDataDto userDataDto) throws IOException;
}
