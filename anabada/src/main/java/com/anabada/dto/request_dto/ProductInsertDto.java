package com.anabada.dto.request_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;

@Getter
@Setter
public class ProductInsertDto {

    private String name;
    private String usingDate;

    private String detail;
    private Integer price;

    private Long category;



    public Product getProduct(@AuthenticationPrincipal MemberDetailDTO principal){
        Member member = Member.builder().memberNo(principal.getNo()).build();
        Product product = Product.builder()
                .productDetail(detail)
                .productName(name)
                .productUseDate(usingDate)
                .member(member)
                .productPrice(price)
                .build();

        return product;
    }
}
