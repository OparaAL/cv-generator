package com.application.cvgenerator.model.user;

import com.application.cvgenerator.model.BaseEntity;
import com.application.cvgenerator.model.cv.CvEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "user_data")
@Data
public class UserDataEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String goal;
    private int age;

    @OneToMany(mappedBy = "userData")
    private List<ExperienceEntity> experiences;

    @OneToOne(mappedBy = "userData")
    private CvEntity cvEntity;
}
