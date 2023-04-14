package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.response_dto.BidInfoFindDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Bid;
import com.anabada.entity.Product;
import com.anabada.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;

    public Long bidSave(BidInsertDto bidInsertDto ,MemberDetailDTO memberDetailDTO) {
        Bid bid = bidInsertDto.getBid(memberDetailDTO);
        bidRepository.save(bid);
        return bid.getProduct().getProductNo();
    }


    public ResultList<Integer,List<BidInfoFindDto>> findBidList(Long productNo){
        Product product = Product.builder().productNo(productNo).build();
        List<Bid> bidList = bidRepository.findBidByProduct(product);

        List<BidInfoFindDto> bidInfoFindDtoList = bidList.stream().map(bid -> new BidInfoFindDto(bid)).collect(Collectors.toList());
        ResultList resultList = new ResultList<>(bidList.size(),bidInfoFindDtoList);
        return resultList;

    }
}
