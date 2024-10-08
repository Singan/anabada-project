package com.anabada.repository;

import com.anabada.entity.ChatRoom;
import com.anabada.entity.Member;
import com.anabada.entity.SuccessfulBid;
import com.anabada.entity.nativeQuery.ChatRoomInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {


    @Query(value = "select " +
            "c1.chat_room_no chatRoomNo, " +
            "c1.member_no memberNo," +
            "c1.success_no successNo," +
            "m.member_name memberName," +
            "m.member_image memberImage," +
            "b.bid_price bidPrice," +
            "p.product_name productName," +
            "m.member_wish_addr memberWishAddr," +
            "p.product_thumbnail productThumbnail " +
            " from chat_room c1 " +
            "join (select c2.* from chat_room c2 where c2.member_no =:memberNo) c2 " +
            "on c1.success_no = c2.success_no and c1.member_no not in(c2.member_no) " +
            "join successful_bid s on c1.success_no= s.success_bid_product_no " +
            "join product p on s.product_no = p.product_no " +
            "join bid b on s.bid_no = b.bid_no " +
            "join member m on c1.member_no = m.member_no" , nativeQuery = true)
    List<ChatRoomInterface> findChatRoomByMember(@Param("memberNo") Long memberNo);

    @Query("select ch from ChatRoom ch where ch.member =:member and ch.successfulBid = :succ")
    ChatRoom findChatRoomBySuccessfulBidAndMember(@Param("succ") SuccessfulBid successfulBid,@Param("member") Member member);
}
