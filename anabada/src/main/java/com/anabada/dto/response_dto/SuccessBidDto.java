package com.anabada.dto.response_dto;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.entity.Bid;
import com.anabada.entity.SuccessfulBid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessBidDto {
    private String memberName;
    private Integer productPrice;
    private String productName;
    private String productUseDate;
    private String productThumbnail;
    private String productUploadMember;
    private String wishAddr;

    public SuccessBidDto(SuccessfulBid successfulBid, String prefix) {
        this.memberName = successfulBid.getBid().getMember().getMemberName();
        this.productPrice = successfulBid.getProduct().getProductPrice();
        this.productName = successfulBid.getProduct().getProductName();
        this.productUseDate = successfulBid.getProduct().getProductUseDate();
        this.productThumbnail = prefix + successfulBid.getProduct().getProductThumbnail();
        this.productUploadMember = successfulBid.getProduct().getMember().getMemberName();
        this.wishAddr = successfulBid.getProduct().getMember().getMemberWishAddr();
    }

}
