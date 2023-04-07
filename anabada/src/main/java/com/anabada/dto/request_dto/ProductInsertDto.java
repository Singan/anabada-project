package com.anabada.dto.request_dto;

import com.anabada.entity.Member;
import com.anabada.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInsertDto {

    private String name;
    private String usingDate;

    private String detail;
    private Integer price;

    private Long category;



    public Product getProduct(){
        Member member = Member.builder().memberNo(1L).build();
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
