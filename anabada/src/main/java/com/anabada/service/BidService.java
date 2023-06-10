package com.anabada.service;

import com.anabada.dto.MemberDetailDTO;
import com.anabada.dto.request_dto.BidInsertDto;
import com.anabada.dto.response_dto.BidInfoFindDto;
import com.anabada.dto.response_dto.BidInsertResponseDto;
import com.anabada.dto.response_dto.ResultList;
import com.anabada.entity.Bid;
import com.anabada.entity.Member;
import com.anabada.entity.Product;
import com.anabada.entity.nativeQuery.MaxBidProductNoInterface;
import com.anabada.etc.BidManagement;
import com.anabada.repository.BidRepository;
import com.anabada.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BidService {

    private final BidRepository bidRepository;
    @Value("${s3.bucket.endpoint}")
    private String prefix;
    private final ProductRepository productRepository;
    private final BidManagement bidManagement;
    @Transactional
    public BidInsertResponseDto bidSave(BidInsertDto bidInsertDto, MemberDetailDTO memberDetailDTO) {
        Member member = memberDetailDTO.getMember();
        Bid insertBid = bidInsertDto.getBid(member);
        Product p = insertBid.getProduct();
        boolean b = productRepository.readProductByProductIsBidComplete(p.getProductNo());

        if(b==true){
            throw new RuntimeException("경매를 할 수 없는 상품입니다.");
        }

        Bid currBid = bidRepository.findFirstByProductOrderByTimeDesc(p);
        if (currBid == null) {
            bidRepository.save(insertBid);// 먼저 현재 진행중인 입찰이 있나 체크 없다면 이후 조건을 체크하지않고 넘어가기 위함
            bidManagement.updateHaspMap(p,insertBid);
        } else {
            if (insertBid.getPrice() <= currBid.getPrice()) {
                throw new RuntimeException("새로운 입찰은 현재 입찰가보다 작을 수 없습니다.");
                // 만약 현재 진행중인 입찰이 있다면 등록 가격을 비교,사실상 비정상적인 API 호출을 막기위한 검증
            } else {
                bidRepository.save(insertBid);
                bidManagement.updateHaspMap(p,insertBid);
            }
        }
        String bidTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh시 mm분"));
        BidInsertResponseDto bidRes = BidInsertResponseDto
                .builder()
                .memberImage(bidInsertDto.getMemberImage())
                .memberNo(member.getMemberNo())
                .price(insertBid.getPrice())
                .memberName(member.getMemberName())
                .productNo(p.getProductNo())
                .bidTime(bidTime)
                .productName(p.getProductName())
                .build();
        return bidRes;
    }


    public ResultList<List<BidInfoFindDto>> findBidList(Long productNo) {
        Product product = Product.builder().productNo(productNo).build();
        List<Bid> bidList = bidRepository.findBidListByProduct(product);

        List<BidInfoFindDto> bidInfoFindDtoList = bidList.stream().map(bid -> new BidInfoFindDto(bid, prefix)).collect(Collectors.toList());
        ResultList resultList = new ResultList<>(bidInfoFindDtoList);
        return resultList;

    }


    //입찰 내역 중 최고값의 시간이 입력 후 10분이 지난 입찰 내역의 상품 번호
    public List<MaxBidProductNoInterface> productByMaxBidList(){
        return bidRepository.bidList();
    }
    @Transactional
    public void updateProductBidSuccess(List<Long> productNoList){
        bidRepository.updateProductSuccessBid(productNoList);
    }
}
