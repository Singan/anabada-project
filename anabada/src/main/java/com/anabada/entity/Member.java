package com.anabada.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long memberNo;

    private String memberName;

    private String memberId;
    private String memberPassword;


}
