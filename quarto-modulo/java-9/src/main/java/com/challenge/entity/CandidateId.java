package com.challenge.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class CandidateId implements Serializable {

    @ManyToOne
    private User userId;

    @ManyToOne
    private Acceleration accelerationId;

    @ManyToOne
    private Company companyId;

}
