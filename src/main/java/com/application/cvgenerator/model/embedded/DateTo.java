package com.application.cvgenerator.model.embedded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class DateTo {
    private int monthTo;
    private int yearTo;
}
