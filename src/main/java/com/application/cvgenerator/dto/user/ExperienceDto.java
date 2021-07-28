package com.application.cvgenerator.dto.user;

import com.application.cvgenerator.dto.BaseDto;
import com.application.cvgenerator.model.embedded.DateFrom;
import com.application.cvgenerator.model.embedded.DateTo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;

@Getter
@Setter
public class ExperienceDto extends BaseDto {

    private String position;
    private String companyName;

    @Embedded
    private DateFrom dateFrom;

    @Embedded
    private DateTo dateTo;
}
