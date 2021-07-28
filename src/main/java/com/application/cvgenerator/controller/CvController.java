package com.application.cvgenerator.controller;

import com.application.cvgenerator.dto.cv.CvDto;
import com.application.cvgenerator.dto.user.UserDataDto;
import com.application.cvgenerator.service.interfaces.cv.CvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/cv")
@RequiredArgsConstructor
public class CvController {

    private final CvService cvService;

    @GetMapping("/{cvId}")
    public ResponseEntity<Void> getCv(@PathVariable Long cvId, @RequestParam String path) throws IOException {
        cvService.downloadCv(cvId, path);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CvDto> createCv(@RequestBody UserDataDto userDataDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(cvService.createCv(userDataDto));
    }

    @PutMapping("/{cvId}")
    public ResponseEntity<CvDto> updateCv(@RequestBody UserDataDto userDataDto, @PathVariable Long cvId) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(cvService.updateCv(userDataDto, cvId));
    }

    @DeleteMapping("/{cvId}")
    public ResponseEntity<Void> deleteCv(@PathVariable Long cvId){
        cvService.delete(cvId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
