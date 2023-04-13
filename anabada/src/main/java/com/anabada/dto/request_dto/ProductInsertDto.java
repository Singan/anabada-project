package com.anabada.dto.request_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Category;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

@Getter
@Setter
@RequiredArgsConstructor
public class ProductInsertDto {

    private String name;
    private String usingDate;

    private String detail;
    private Integer price;

    private String categoryName;



    public Product getProduct(MemberDetailDTO principal){
        Member member = Member.builder().memberNo(principal.getNo()).build();
        Category category = Category.builder().categoryName(categoryName).build();
        Product product = Product.builder()
                .productDetail(detail)
                .productName(name)
                .productUseDate(usingDate)
                .member(member)
                .productPrice(price)
                .category(category)
                .build();

        return product;
    }
}
