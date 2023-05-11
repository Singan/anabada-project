package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.response_dto.BidInfoFindDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Bid;
import com.anabada.entity.CurrentBid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.repository.BidRepository;
import com.anabada.repository.CurrentBidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    private final CurrentBidRepository currentBidRepository;

    @Transactional
    public void bidSave(BidInsertDto bidInsertDto, MemberDetailDTO memberDetailDTO) {
        Member member = memberDetailDTO.getMember();
        Bid bid = bidInsertDto.getBid(member);
        Product product = bid.getProduct();
        CurrentBid currBid = currentBidRepository.findById(product.getProductNo()).get();
        if (currBid == null) {
            bidRepository.save(bid);// 먼저 현재 진행중인 입찰이 있나 체크 없다면 이후 조건을 체크하지않고 넘어가기 위함
            CurrentBid currentBid = CurrentBid.builder()
                    .localDateTime(LocalDateTime.now())
                    .member(member)
                    .price(bid.getPrice())
                    .product(product)
                    .build();
            currentBidRepository.save(currentBid);
        } else {
            if (bid.getPrice() <= currBid.getPrice()) {
                throw new RuntimeException("새로운 입찰은 현재 입찰가보다 작을 수 없습니다.");
                // 만약 현재 진행중인 입찰이 있다면 등록 가격을 비교,사실상 비정상적인 API 호출을 막기위한 검증
            } else {
                bidRepository.save(bid);
                currBid.updateCurrentBid(member, bid.getPrice());
            }

        }
    }




    public ResultList<List<BidInfoFindDto>> findBidList(Long productNo) {
        Product product = Product.builder().productNo(productNo).build();
        List<Bid> bidList = bidRepository.findBidListByProduct(product);

        List<BidInfoFindDto> bidInfoFindDtoList = bidList.stream().map(bid -> new BidInfoFindDto(bid)).collect(Collectors.toList());
        ResultList resultList = new ResultList<>(bidInfoFindDtoList);
        return resultList;

    }
}
