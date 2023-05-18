package com.anabada.dto.response_dto;

import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class BidInfoFindDto {

    private String memberName;
    private Integer price;
    private String memberImage;

    private String bidTime;

    public BidInfoFindDto(Bid bid,String prefix) {
        Member member = bid.getMember();
        this.memberName = member.getMemberName();
        this.memberImage = prefix+member.getMemberImage();
        this.price = bid.getPrice();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh시 mm분");
        this.bidTime = bid.getTime().format(dateTimeFormatter);
    }
}
