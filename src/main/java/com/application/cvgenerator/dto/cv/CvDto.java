package com.application.cvgenerator.dto.cv;

import com.application.cvgenerator.dto.BaseDto;
import com.application.cvgenerator.dto.user.UserDataDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CvDto extends BaseDto {

    private String fileName;
    private String fileUrl;

    private UserDataDto userData;
}
