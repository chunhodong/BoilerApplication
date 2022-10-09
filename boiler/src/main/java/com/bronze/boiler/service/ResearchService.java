package com.bronze.boiler.service;

import com.bronze.boiler.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResearchService {
    private final MemberRepository memberRepository;

    //--------------------------회원조회-----------------------
    //회원수조회
    //년/월/일별 가입자수
    //등급별 회원수
    //회원이름순정렬
    //아이디별정렬
    //가입날짜별(최신순/오래된순)정렬
    //%이메일%조회
    //상태값조회
    //최대주문회원

    public long getMemberCount(){
        return memberRepository.count();
    }

    //--------------------------상품조회------------------------
    //가장비싼상품
    //가장저렴한상품
    //가격범위별조회회
    //카테고리별상품조회

    //---------------------------주문조회-----------------------
    //가장 많은 주문상품
    //가장 적은 주문상품
    //월/일 별 주문상품
    //월/일 별 매출
    //날짜 별 주문
    //상태별(주문,주문취소) 주문 조회
    //시그니처별주문
    //사용자별주문
    //날짜순주문정렬

    //-----------------------------대량수정
    //-----------------------------대량추가

}
