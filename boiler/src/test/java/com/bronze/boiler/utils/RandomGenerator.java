package com.bronze.boiler.utils;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.member.domain.Member;
import com.bronze.boiler.member.domain.MemberStatus;
import com.bronze.boiler.member.domain.Role;
import com.bronze.boiler.domain.order.entity.Address;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.order.enums.OrderStatus;
import com.bronze.boiler.domain.payment.entity.Payment;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.enums.ProductStatus;

import java.time.LocalDateTime;
import java.util.*;


public class RandomGenerator {
    private static Random random = new Random();

    private static Long[] prices = new Long[]{20000l,50000l,15000l,30000l,40000l,33000l};
    public static String getRandomStr(int size) {
        if(size > 0) {
            char[] tmp = new char[size];
            for(int i=0; i<tmp.length; i++) {
                int div = (int) Math.floor( Math.random() * 2 );

                if(div == 0) {
                    tmp[i] = (char) (Math.random() * 10 + '0') ;
                }else {
                    tmp[i] = (char) (Math.random() * 26 + 'A') ;
                }
            }
            return new String(tmp);
        }
        return "ERROR : Size is required.";
    }
    public static String getKoreanFullName() {
        List< String > 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성", "차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함", "변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명", "기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구", "용");
        List < String > 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누", "다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민", "바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔", "수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용", "우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정", "제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판", "하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림", "봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴", "언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란", "더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱", "평", "늘", "늬", "랑", "얀", "향", "울", "련");
        Collections.shuffle(성);
        Collections.shuffle(이름);
        return 성.get(0) + 이름.get(0) + 이름.get(1);
    }

    public static Address getAddress() {
        List< String > titles = Arrays.asList("서울시 강동구","서울시 강서구","경기도 성남시 상도동","경기도 안양시 범내천","경기도 광명시 수지동");
        List<String> details = Arrays.asList("은마아파트 105동 602호","푸르지오 602동 107호","강동자이 102호 302호","강동현대아파트 1032동 203호","성내동 이코빌라 102호");
        int zipCode = random.nextInt(100) + 10000;
        Collections.shuffle(titles);
        Collections.shuffle(details);
        return Address.builder()
                .titleAddress(titles.get(0))
                .detailAddress(details.get(0))
                .zipcode(Long.valueOf(zipCode))
                .build();

    }



    public static String getProductName() {
        return "상품".concat(UUID.randomUUID().toString());

    }
    public static String getTel(){

        int x = random.nextInt(9000) + 1000; //마지막자리 4자리수 1000~9999

        return "010-".concat(String.valueOf(x)).concat("-").concat(String.valueOf(x));

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


    static class CardSet{
        String code;
        String name;
        public CardSet(String code,String name){
            this.code = code;
            this.name = name;
        }
    }


    public static List<CardSet> cardSets = List.of(
            new CardSet("07","신한"),
            new CardSet("51","국민"),
            new CardSet("42","우리"),
            new CardSet("38","BC"),
            new CardSet("05","현대"),
            new CardSet("17","삼성"),
            new CardSet("23","롯데"),
            new CardSet("15","농협"));
    public static CardSet getCardCode(){


        return cardSets.get(random.nextInt(cardSets.size()));
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

    public static Orders getOrder(){

        long price = prices[random.nextInt(prices.length)];
        return Orders.builder()
                .address(getAddress())
                .totalPrice(price)
                .paymentPrice(price)
                .discountPrice(0l)
                .status(OrderStatus.PAYMENT)
                .member(Member.builder().id(Long.valueOf(random.nextInt(6000000) + 1)).build())
                .build();
    }

    public static ProductImage getProductImage(Long productId){
        //1 2 4 5 6 7
        //3000000
        //3000001
        //6000000
        //
        return ProductImage.builder()
                .name(getRandomStr(20).concat(".").concat("png"))
                .path("/product/image/")
                .product(Product.builder().id(productId > 3000000 ? productId - 3000000 : productId).build())
                .domain("https://boiler.com")
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
                .hasOption(random.nextInt(2) % 2 == 0 ? true : false)
                .savePoint(50l)
                .status(statuses.get(random.nextInt(4)))
                .refundInfo("인터넷쇼핑몰 사업자는 위 2.부터 5.까지의 사유에 해당하여 청약철회 등이 불가능한 상품에 대해 그 사실을 상품의 포장이나 그 밖에 소비자가 쉽게 알 수 있는 곳에 명확하게 적거나 시험 사용 상품을 제공하는 등의 방법으로 청약철회 등의 권리 행사가 방해받지 않도록 조치해야 합니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제6항 본문). 만약 사업자가 이와 같은 조치를 안했다면, 소비자는 청약철회 등의 제한사유에도 불구하고 청약철회 등을 할 수 있습니다(「전자상거래 등에서의 소비자보호에 관한 법률」 제17조제2항 단서).")
                .sellerInfo("주식회사 퓨처웍스에서 최급하는 상품입니다 판매자사유를 위반하는 경우 최대10개월의 징역이나 3000만원의 벌금에 취할수있습니다")
                .sizeInfo("사이즈는 사진과 비교시에 1-2센치오차가 있을수있습니다")
                .build();
    }

    public static Payment getPayment() {
        CardSet cardSet = getCardCode();
        String signature = UUID.randomUUID().toString().replace("-","");
        String moid = UUID.randomUUID().toString().replace("-","");

        return Payment.builder()
                .buyerName(getKoreanFullName())
                .buyerTel(getTel())
                .buyerEmail(getEmail())
                .cardCode(cardSet.code)
                .cardName(cardSet.name)
                .cardQuota(0l)
                .moid(moid)
                .mid("sugarmall")
                .resultCode("3001")
                .member(Member.builder().id(Long.valueOf(random.nextInt(5999999) + 1)).build())
                .signature(signature)
                .build();
    }
}
