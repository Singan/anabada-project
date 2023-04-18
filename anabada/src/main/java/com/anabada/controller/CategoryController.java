package com.anabada.controller;

import com.anabada.dto.response_dto.CategoryFindDto;
import com.anabada.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryFindDto> categoryFindDto() {
        return categoryService.getCategoryList();
    }
}
