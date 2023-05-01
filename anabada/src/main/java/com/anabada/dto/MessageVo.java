package com.anabada.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@ToString
public class MessageVo {
    private String memberId;
    private String message;
}
