package com.bronze.boiler.utils;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.*;


public class RandomGenerator {
    private static Random random = new Random();

    public static String getKoreanFullName() {
        List< String > 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List < String > 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }


    public static String getProductName() {
        return "상품".concat(UUID.randomUUID().toString());

    }

    public static String getEmail(){
        List<String> domains = Arrays.asList("@naver.com","@google.com");
        Collections.shuffle(domains);

        String randomStr = new Random().ints(97, 123)
                .limit(10)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return randomStr.concat(domains.get(0));
    }

    public static String getPassword(){
        return UUID.randomUUID().toString();
    }

    public static LocalDateTime getLocalDateTime(){

        return LocalDateTime.now().minusDays(new Random().nextInt(100));
    }

    public static Member getMember(){

        return Member.builder().name(getKoreanFullName())
                .email(getEmail())
                .password(getPassword())
                .role(Role.USER)
                .status(MemberStatus.NORMAL)
                .lastLogin(getLocalDateTime())
                .build();
    }

    public static Product getProduct(Category category){

        List<ProductStatus> statuses = List.of(ProductStatus.CLOSE,ProductStatus.NEW,ProductStatus.SELL,ProductStatus.SOLDOUT);
        long price = Long.valueOf(random.nextInt((300000 - 10000) + 1) + 10000);

        return Product.builder()
                .name(getProductName())
                .code(UUID.randomUUID().toString())
                .description("상품설명입니다".concat(UUID.randomUUID().toString()))
                .originPrice(price)
                .sellPrice(price)
                .category(category)
                .hasOption(random.nextInt(1) % 2 == 0 ? true : false)
                .savePoint(50l)
                .status(statuses.get(random.nextInt(4)))
                .refundInfo("인터넷쇼핑몰 사업자는 위 2.부터 5.까지의 사유에 해당하여 청약철회 등이 불가능한 상품에 대해 그 사실을 상품의 포장이나 그 밖에 소비자가 쉽게 알 수 있는 곳에 명확하게 적거나 시험 사용 상품을 제공하는 등의 방법으로 청약철회 등의 권리 행사가 방해받지 않도록 조치해야 합니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제6항 본문). 만약 사업자가 이와 같은 조치를 안했다면, 소비자는 청약철회 등의 제한사유에도 불구하고 청약철회 등을 할 수 있습니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제2항 단서).")
                .sellerInfo("주식회사 퓨처웍스에서 최급하는 상품입니다 판매자사유를 위반하는 경우 최대10개월의 징역이나 3000만원의 벌금에 취할수있습니다")
                .sizeInfo("사이즈는 사진과 비교시에 1-2센치오차가 있을수있습니다")
                .build();
    }

}
