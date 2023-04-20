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

    public void categorySave() {

        Category c1 = new Category("의류");
        Category c2 = new Category("도서");
        Category c3 = new Category("뷰티");
        Category c4 = new Category("전자제품");
        Category c5 = new Category("주방용품");
        Category c6 = new Category("문구류");
        List<Category> categories = Arrays.asList(c1, c2, c3, c4, c5, c6);
        categoryRepository.saveAll(categories);

    }
}
