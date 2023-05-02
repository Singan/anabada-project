package com.anabada.dto.response_dto;

import com.anabada.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CategoryFindDto {

    private Long categoryNo;
    private String categoryName;

    public CategoryFindDto(Category category) {
        this.categoryNo = category.getCategoryNo();
        this.categoryName = category.getCategoryName();
    }
}
