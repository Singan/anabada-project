package com.anabada.dto;

import com.anabada.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberLoginDto {


    private String id;
    private String pw;

}