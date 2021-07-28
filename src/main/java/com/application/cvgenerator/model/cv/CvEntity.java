package com.application.cvgenerator.model.cv;

import com.application.cvgenerator.model.BaseEntity;
import com.application.cvgenerator.model.user.UserDataEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "generated_cv")
@Data
public class CvEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_data_id")
    private UserDataEntity userData;

    private String fileName;
    private String fileUrl;
}
