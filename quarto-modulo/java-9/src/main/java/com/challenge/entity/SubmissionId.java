package com.challenge.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class SubmissionId implements Serializable {

    @ManyToOne
    private User userId;

    @ManyToOne
    private Challenge challengeId;
}
