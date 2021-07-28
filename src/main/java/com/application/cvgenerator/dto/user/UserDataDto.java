package com.application.cvgenerator.dto.user;

import com.application.cvgenerator.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDataDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String goal;
    private int age;
    List<ExperienceDto> experiences;
}
