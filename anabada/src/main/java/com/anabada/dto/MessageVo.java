package com.anabada.dto;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class MessageVo {
    private String memberId;
    private String message;
}
