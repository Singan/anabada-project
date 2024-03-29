package com.anabada.config.token;

import com.anabada.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider{
    private  String secretKey = "ASDKJSNKDQnskdnakjsdnkajsndkjqnksdqQASDQSDasdfsqqdsfqsd" ;

    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     *
     * @param member : 사용자 정보
     * @return String : 토큰
     */
    public String generateJwtToken(final Member member) {
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())                              // Header 구성
                .setClaims(createClaims(member))                       // Payload - Claims 구성
                .setSubject(String.valueOf(member.getMemberNo()))        // Payload - Subject 구성
                .signWith(SignatureAlgorithm.HS256, createSignature())  // Signature 구성
                .setExpiration(createExpiredDate());                    // Expired Date 구성
        return builder.compact();
    }

    /**
     * 유효한 토큰인지 확인 해주는 메서드
     *
     * @param token String  : 토큰
     * @return boolean      : 유효한지 여부 반환
     */
    public boolean isValidToken(String token) {
        if(token == null || token.isBlank())
            return false;
        try {
            Claims claims = getClaimsFormToken(token);
            if(null!=claims){
                return true;
            }
            return false;
        } catch (ExpiredJwtException exception) {
            throw new JwtException("토큰 만료");
        } catch (JwtException exception) {
            throw new JwtException("Token Tampered");
        } catch (NullPointerException exception) {
            throw new JwtException("토큰이 존재하지 않습니다.");
        }
    }

    /**
     * Header 내에 토큰을 추출합니다.
     *
     * @param header 헤더
     * @return String
     */
    public String getTokenFromHeader(final String header) {
        return header.split(".")[1];
    }

    /**
     * 토큰의 만료기간을 지정하는 함수
     *
     * @return Calendar
     */
    private Date createExpiredDate() {
        // 토큰 만료시간은 30일으로 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR, 24);     // 24시간
        // c.add(Calendar.DATE, 1);         // 1일
        return c.getTime();
    }

    /**
     * JWT의 "헤더" 값을 생성해주는 메서드
     *
     * @return HashMap<String, Object>
     */
    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();

        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    /**
     * 사용자 정보를 기반으로 클래임을 생성해주는 메서드
     *
     * @param Member 사용자 정보
     * @return Map<String, Object>
     */
    private Map<String, Object> createClaims(final Member Member) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", Member.getMemberId());
        claims.put("userNo", Member.getMemberNo());
        claims.put("userName", Member.getMemberName());
        return claims;
    }

    /**
     * JWT "서명(Signature)" 발급을 해주는 메서드
     *
     * @return Key
     */
    private Key createSignature() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    /**
     * 토큰 정보를 기반으로 Claims 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return Claims : Claims
     */
    private Claims getClaimsFormToken(final String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
    }

    /**
     * 토큰을 기반으로 사용자 정보를 반환받는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public Member getTokenConverterMember(final String token) {
        Claims claims = getClaimsFormToken(token);
        String id = claims.get("userId").toString();
        Long no = Long.parseLong(claims.get("userNo").toString());
        String name = claims.get("userName").toString();
        Member member = Member
                .builder()
                .memberId(id)
                .memberNo(no)
                .memberName(name)
                .build();
        return member;
    }

}


