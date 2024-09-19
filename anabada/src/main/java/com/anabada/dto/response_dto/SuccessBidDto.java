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
    private Long productNo;
    private Long successBidNo;
    private Long bidNo;
    private Long buyerNo;
    private Long sellerNo;
    private String status;
    public SuccessBidDto(SuccessfulBid successfulBid, String prefix) {
        this.memberName = successfulBid.getBid().getMember().getMemberName();
        this.productPrice = successfulBid.getBid().getPrice();
        this.productName = successfulBid.getBid().getProduct().getProductName();
        this.productUseDate = successfulBid.getBid().getProduct().getProductUseDate();
        this.productThumbnail = prefix + successfulBid.getBid().getProduct().getProductThumbnail();
        this.productUploadMember = successfulBid.getBid().getProduct().getMember().getMemberName();
        this.wishAddr = successfulBid.getBid().getProduct().getMember().getMemberWishAddr();
        this.successBidNo = successfulBid.getSuccessBidProductNo();
        this.productNo = successfulBid.getBid().getProduct().getProductNo();
        this.bidNo = successfulBid.getBid().getBidNo();
        this.buyerNo = successfulBid.getBid().getBidNo();
        this.sellerNo = successfulBid.getBid().getProduct().getProductNo();
        this.status = successfulBid.getStatus().name();
    }

}
