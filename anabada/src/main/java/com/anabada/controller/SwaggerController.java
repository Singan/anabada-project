package com.anabada.controller;

import com.anabada.entity.Member;
import com.anabada.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class SwaggerController {

    @GetMapping("/swagger-ui")
    public String homeRedirect() {
        return "redirect:/swagger-ui/index.html";
    }
}