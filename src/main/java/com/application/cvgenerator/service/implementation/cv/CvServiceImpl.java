package com.application.cvgenerator.service.implementation.cv;

import com.application.cvgenerator.dto.cv.CvDto;
import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.exception.ItemNotFoundException;
import com.application.cvgenerator.mapper.CvMapper;
import com.application.cvgenerator.model.cv.CvEntity;
import com.application.cvgenerator.model.user.UserDataEntity;
import com.application.cvgenerator.repository.cv.CvRepository;
import com.application.cvgenerator.service.interfaces.cv.CvService;
import com.application.cvgenerator.service.interfaces.cv.PdfService;
import com.application.cvgenerator.service.interfaces.s3.S3Service;
import com.application.cvgenerator.service.interfaces.user.UserDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CvServiceImpl implements CvService {

    private final String defaultDownloadsFolder = System.getProperty("user.home")+"/Downloads/";

    private final UserDataService userDataService;
    private final CvRepository cvRepository;
    private final CvMapper cvMapper;
    private final PdfService pdfService;
    private final S3Service s3Service;

    @Override
    public void downloadCv(Long cvId, String path) throws IOException {
        CvEntity cv = getCv(cvId);
        URL url = new URL(cv.getFileUrl());
        FileUtils.copyURLToFile(url, new File(path == null || path.isEmpty()
                ? defaultDownloadsFolder + cv.getFileName()
                : path + "//" + cv.getFileName()));
    }

    @Override
    @Transactional
    public CvDto createCv(UserDataDto userDataDto) throws IOException {
        CvEntity cv = new CvEntity();
        Map<String, byte[]> file = pdfService.createPdf(userDataDto);

        cv.setUserData(userDataService.createUserData(userDataDto));
        cv.setFileName(getFilename(file));
        cv.setFileUrl(s3Service.upload(file));

        cvRepository.save(cv);
        return cvMapper.cvEntityToDto(cv);
    }

    @Override
    @Transactional
    public CvDto updateCv(UserDataDto userDataDto, Long cvId) throws IOException {

        CvEntity findCv = cvRepository.findById(cvId)
                .orElseThrow(() -> new ItemNotFoundException("Cv not found"));
        Map<String, byte[]> file = pdfService.createPdf(userDataDto);

        s3Service.delete(findCv.getFileName());

        CvEntity cv = new CvEntity();

        cv.setUserData(userDataService.updateUserData(userDataDto, findCv.getUserData().getId()));
        cv.setFileName(getFilename(file));
        cv.setFileUrl(s3Service.upload(file));

        cv.setId(findCv.getId());
        cvRepository.save(cv);

        return cvMapper.cvEntityToDto(cv);
    }

    @Override
    @Transactional
    public void delete(Long cvId) {

        CvEntity cv = getCv(cvId);
        UserDataEntity deleteUserData = cv.getUserData();
        cv.setUserData(null);
        s3Service.delete(cv.getFileName());
        userDataService.deleteUserData(deleteUserData);
        cvRepository.delete(cv);
    }

    private CvEntity getCv(Long cvId){
        return cvRepository.findById(cvId)
                .orElseThrow(() -> new ItemNotFoundException("Cv not found"));
    }

    private String getFilename(Map<String, byte[]> file){
        return file.keySet().stream().findFirst().orElseThrow(() -> new ItemNotFoundException("Error while creating cv"));
    }
}
