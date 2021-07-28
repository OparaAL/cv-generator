package com.application.cvgenerator.model.user;

import com.application.cvgenerator.model.BaseEntity;
import com.application.cvgenerator.model.embedded.DateFrom;
import com.application.cvgenerator.model.embedded.DateTo;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_experience")
@Data
public class ExperienceEntity extends BaseEntity {

    private String position;
    private String companyName;

    @Embedded
    private DateFrom dateFrom;

    @Embedded
    private DateTo dateTo;

    @ManyToOne
    @JoinColumn(name = "user_data_id")
    private UserDataEntity userData;
}
