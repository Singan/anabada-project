package com.anabada.service;

import com.anabada.dto.response_dto.CategoryFindDto;
import com.anabada.entity.Category;
import com.anabada.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryFindDto> getCategoryList() {

        List<Category> categoryList = categoryRepository.findAll();
        ArrayList<CategoryFindDto> categoryFindDtoArrayList = new ArrayList<>();

        categoryList.forEach(categorys -> {
            CategoryFindDto categoryFindDto = new CategoryFindDto(categorys);
            categoryFindDtoArrayList.add(categoryFindDto);
        });

        return categoryFindDtoArrayList;
    }


}
