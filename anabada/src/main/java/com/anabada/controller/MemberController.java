package com.anabada.controller;

import com.anabada.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping
    public String aa(){
        return "aa";
    }

    @GetMapping
    public String bb() {
        return "bb";
    }

    @GetMapping
    public String dd() {
        return "dd";
    }

    public String cc() {
        return "cc";

    }

}
