package com.anabada.dto.request_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductInsertDto {
    @NotBlank
    private String name;
    @NotBlank
    private String usingDate;

    private String detail;
    @NotBlank
    private Integer price;
    @NotBlank
    private Long categoryNo;
    private List<MultipartFile> productImages;

    @JsonIgnore
    public Product getProduct(MemberDetailDTO principal,String thumbnail ){
        Member member = principal.getMember();
        Category category = Category.builder().categoryNo(categoryNo).build();
        Product product = Product.builder()
                .productDetail(detail)
                .productName(name)
                .productUseDate(usingDate)
                .productThumbnail(thumbnail)
                .productIsBidComplete(false)
                .localDateTime(LocalDateTime.now())
                .member(member)
                .productVisit(0L)
                .productPrice(price)
                .category(category)
                .build();

        return product;
    }
}
