package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.entity.OrderProduct;
import com.bronze.boiler.domain.order.entity.Orders;
import com.bronze.boiler.domain.payment.entity.Payment;
import com.bronze.boiler.domain.product.entity.*;
import com.bronze.boiler.domain.product.enums.ProductReviewStatus;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.filter.ProductFilter;
import com.bronze.boiler.utils.RandomGenerator;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Import(TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Transactional
@Rollback(value = false)
public class ProductRepositoryTest {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private HikariDataSource hikariDataSource;


    private int batchSize = 50000;


    private Random random = new Random();


    @Test
    void 결제추가() {
        List<Long> ids = selectCanel();
        System.out.println("ids : "+ids.size());


        List<Payment> payments = new ArrayList<>();
        int count = ids.size();
        for (long i = 0; i < count; i++) {



            payments.add(RandomGenerator.getPayment());

        }
        saveAllJdbcBatchPayment(payments,ids);


    }
    public List<Long> selectOrders(){

        return null;
    }

    public List<Long> selectCanel() {
        List<Long> orderIds  = new ArrayList<>();

        String sql =
                "SELECT order_id FROM payment WHERE result_code=?";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, "REFUND");

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                orderIds.add(resultSet.getLong(1));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return orderIds;
        }
    }



    public void saveAllJdbcBatchPayment(List<Payment> payments,List<Long> ids) {
        String sql =
                "INSERT INTO payment (id,created,modified,buyer_email,buyer_name,buyer_tel,card_code,card_name,card_quota,mid,moid,result_code,signature,member_id,order_id) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            int counter = 12000000;
            int idx = 0;
            for (Payment payment : payments) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));

                statement.setString(4,payment.getBuyerEmail());
                statement.setString(5, payment.getBuyerName());
                statement.setString(6, payment.getBuyerTel());

                statement.setString(7, payment.getCardCode());
                statement.setString(8, payment.getCardName());
                statement.setLong(9, payment.getCardQuota());

                statement.setString(10, payment.getMid());
                statement.setString(11, payment.getMoid());

                statement.setString(12, "2001");
                statement.setString(13, payment.getSignature());
                statement.setLong(14, payment.getMember().getId());
                statement.setLong(15, ids.get(idx++));




                statement.addBatch();



                if ((counter + 1) % batchSize == 0 || (counter + 1) == payments.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 주문상품추가() {

        List<OrderProduct> orderProducts = new ArrayList<>();
        int count = 1;
        for (long i = 0; i < count; i++) {



            orderProducts.add(new OrderProduct());

        }
        saveAllJdbcBatchOrdersp(orderProducts);

    }

    public void saveAllJdbcBatchOrdersp(List<OrderProduct> orders) {
        String sql =
                "INSERT INTO order_product (id,created,modified,count,total_price,order_id,product_id,product_option_id) " +
                        "VALUES (?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            int counter = 11999999;
            for (OrderProduct order : orders) {
                int day = random.nextInt(500);
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().minusDays(day)));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().minusDays(day)));

                statement.setLong(4,1);
                statement.setLong(5, 0);
                statement.setLong(6, counter + 1);

                statement.setLong(7, 3000000);
                statement.setLong(8, 6000000);


                statement.addBatch();



                if ((counter + 1) % batchSize == 0 || (counter + 1) == orders.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void 상품옵션추가() {

        List<Orders> orders = new ArrayList<>();
        int count = 2000000;
        for (long i = 0; i < count; i++) {



            orders.add(RandomGenerator.getOrder());

        }
        saveAllJdbcBatchOrders(orders);

    }

    public void saveAllJdbcBatchOrders(List<Orders> orders) {
        String sql =
                "INSERT INTO orders (id,created,modified,detail_address,title_address,zipcode,discount_price,payment_price,status,total_price,member_id) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            int counter = 10000000;
            for (Orders order : orders) {
                int day = random.nextInt(500);
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now().minusDays(day)));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now().minusDays(day)));

                statement.setString(4, order.getAddress().getDetailAddress());
                statement.setString(5, order.getAddress().getTitleAddress());
                statement.setLong(6, order.getAddress().getZipcode());

                statement.setLong(7, order.getDiscountPrice());
                statement.setLong(8, order.getPaymentPrice());
                statement.setString(9, order.getStatus().name());
                statement.setLong(10, order.getTotalPrice());
                statement.setLong(11, order.getMember().getId());



                statement.addBatch();



                if ((counter + 1) % batchSize == 0 || (counter + 1) == orders.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAllJdbcBatchProductOption(List<ProductOption> productOptions) {
        String sql =
                "INSERT INTO product_option (id,created,modified,type,value,product_id) " +
                        "VALUES (?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 3000000;
            for (ProductOption option : productOptions) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.setString(4, option.getType().name());
                statement.setString(5, option.getValue());
                statement.setLong(6, option.getProduct().getId());

                statement.addBatch();



                if ((counter + 1) % batchSize == 0 || (counter + 1) == productOptions.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void 상품리뷰추가() {
        Random random = new Random();
        String[] comments = new String[]{
                "생각보다 잘쓰고있습니다. 가성비가 좋은거같아요",
                        "다시는 안쓰고싶네요 환불절차도 복잡하고 사용법도 너무어려워요",
                        "양이 정말많습니다. 혼자먹기에는 너무 많아요 사시는분들 고려해야될겁니다",
                        "향이 너무 진하네요 환기안되곳이나 좁은공간에서는 냄새가 다 베일거같아요",
                        "배송이 너무 느려요 전화해도 잘 안받고 3일넘어가니 겨우 배송출발한다고 하는데 서비스도 별로입니다",
                        "가격대비 좋습니다 이정도 가격에 이 정도 제품구하기 정말힘든데 싼게 비지쩍이라고 생각하면서 쓰고있습니다",
                        "만족해요 무게가 가벼워서 휴대하기 정말편합니다 근데 배터리는 좀 빨리닳아요",

        };
        List<ProductReview> products = new ArrayList<>();
        int count = 3000000;
        for (long i = 0; i < count; i++) {



            products.add(ProductReview.builder()
                    .status(ProductReviewStatus.NEW)
                    .text("["+i+"]" + comments[random.nextInt(7)])
                    .member(Member.builder().id(Long.valueOf(random.nextInt(3000000) + 1)).build())
                    .product(Product.builder().id(( i % 3000000) + 1).build())
                    .build());

        }
        saveAllJdbcBatchProductReview(products);

    }

    public void saveAllJdbcBatchProductReview(List<ProductReview> productReviews) {
        String sql =
                "INSERT INTO product_review (id,created,modified,status,text,member_id,product_id) " +
                        "VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 27000000;
            for (ProductReview productStock : productReviews) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.setString(4, productStock.getStatus().name());
                statement.setString(5, productStock.getText());
                statement.setLong(6, productStock.getMember().getId());

                statement.setLong(7, productStock.getProduct().getId());


                statement.addBatch();


                if ((counter + 1) % batchSize == 0 || (counter + 1) == productReviews.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void 카테고리추가() {
        Category parentCategory1 = categoryRepository.saveAndFlush(Category.builder().name("패션").build());
        Category category1 = categoryRepository.saveAndFlush(Category.builder().name("신발").parent(parentCategory1).build());
        Category category2 = categoryRepository.saveAndFlush(Category.builder().name("상의").parent(parentCategory1).build());
        Category category3 = categoryRepository.saveAndFlush(Category.builder().name("하의").parent(parentCategory1).build());
        Category category4 = categoryRepository.saveAndFlush(Category.builder().name("속옷").parent(parentCategory1).build());

        Category parentCategory2 = categoryRepository.saveAndFlush(Category.builder().name("가구").build());
        Category category5 = categoryRepository.saveAndFlush(Category.builder().name("책상").parent(parentCategory2).build());
        Category category6 = categoryRepository.saveAndFlush(Category.builder().name("의자").parent(parentCategory2).build());

        Category parentCategory3 = categoryRepository.saveAndFlush(Category.builder().name("기타").build());

        Category parentCategory4 = categoryRepository.saveAndFlush(Category.builder().name("인테리어").build());
        Category category7 = categoryRepository.saveAndFlush(Category.builder().name("침구").parent(parentCategory4).build());
        Category category8 = categoryRepository.saveAndFlush(Category.builder().name("이불").parent(category7).build());
        Category category9 = categoryRepository.saveAndFlush(Category.builder().name("매트").parent(category7).build());
        Category category10 = categoryRepository.saveAndFlush(Category.builder().name("베게").parent(category7).build());

    }

    @Test
    void 상품재고추가() {


        List<ProductStock> products = new ArrayList<>();
        int count = 6000000;
        for (long i = 1; i <= count; i++) {


            products.add(ProductStock.builder().totalStock(100l).product(Product.builder().id(i).build())
                    .currentStock(100l).build());

        }

        saveAllJdbcBatchProductStock(products);
        //productRepository.saveAll(products);


    }

    public void saveAllJdbcBatchProductStock(List<ProductStock> productStocks) {
        String sql =
                "INSERT INTO product_stock (id,created,modified,current_stock,total_stock,product_id) " +
                        "VALUES (?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 0;
            for (ProductStock productStock : productStocks) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.setLong(4, productStock.getCurrentStock());
                statement.setLong(5, productStock.getTotalStock());
                statement.setLong(6, productStock.getProduct().getId());


                statement.addBatch();


                if ((counter + 1) % batchSize == 0 || (counter + 1) == productStocks.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    void 상품추가() {
        Random random = new Random();

        List<Integer> ids = List.of(2, 3, 4, 5, 7, 8, 9, 12, 13, 14);
        List<Category> categories = new ArrayList<>();
        ids.forEach(integer -> categories.add(Category
                .builder()
                .id(Long.valueOf(integer))
                .build()));


        List<Product> products = new ArrayList<>();
        int count = 3000000;
        for (int i = 0; i < count; i++) {

            Category category = categories.get(random.nextInt(categories.size()));

            products.add(RandomGenerator.getProduct(category));

        }

        saveAllJdbcBatch(products);
        //productRepository.saveAll(products);


    }


    @Test
    void 상품이미지추가() {
        Random random = new Random();

        List<Integer> ids = List.of(2, 3, 4, 5, 7, 8, 9, 12, 13, 14);
        List<Category> categories = new ArrayList<>();
        ids.forEach(integer -> categories.add(Category
                .builder()
                .id(Long.valueOf(integer))
                .build()));


        List<ProductImage> productImgs = new ArrayList<>();
        int count = 6000000;
        for (long i = 1; i <= count; i++) {
            productImgs.add(RandomGenerator.getProductImage(i));
        }

        saveAllJdbcBatchProductImgs(productImgs);
        //productRepository.saveAll(products);


    }

    public void saveAllJdbcBatchProductImgs(List<ProductImage> productImages) {
        String sql =
                "INSERT INTO product_image (id,created,modified,name,domain,path,product_id) " +
                        "VALUES (?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 0;
            for (ProductImage product : productImages) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                statement.setString(4, product.getName());
                statement.setString(5, product.getDomain());
                statement.setString(6, product.getPath());
                statement.setLong(7, product.getProduct().getId());


                statement.addBatch();


                if ((counter + 1) % batchSize == 0 || (counter + 1) == productImages.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAllJdbcBatch(List<Product> productData) {
        String sql =
                "INSERT INTO product (id,code, description, has_option,name,origin_price,refund_info,save_point,sell_price,seller_info,size_info,status,category_id,created,modified) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            int counter = 0;
            for (Product product : productData) {
                statement.clearParameters();
                statement.setLong(1, counter + 1);
                statement.setString(2, product.getCode());
                statement.setString(3, product.getDescription());
                statement.setBoolean(4, product.isHasOption());
                statement.setString(5, product.getName());

                statement.setLong(6, product.getOriginPrice());
                statement.setString(7, product.getRefundInfo());
                statement.setLong(8, product.getSavePoint());
                statement.setLong(9, product.getSellPrice());
                statement.setString(10, product.getSellerInfo());
                statement.setString(11, product.getSizeInfo());
                statement.setString(12, product.getStatus().name());
                statement.setLong(13, product.getCategory().getId());
                statement.setTimestamp(14, Timestamp.valueOf(LocalDateTime.now()));
                statement.setTimestamp(15, Timestamp.valueOf(LocalDateTime.now()));

                statement.addBatch();


                if ((counter + 1) % batchSize == 0 || (counter + 1) == productData.size()) {
                    statement.executeBatch();
                    statement.clearBatch();
                }
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void 상품필드수정() {

        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .category(category)
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());
        product.modifySellprice(100000L);


    }


    @Test
    void 상품모든필드수정() {
        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .category(category)
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

        productRepository.save(Product.builder()
                .id(product.getId())
                .name("상품123")
                .code("AEOAK001")
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());

    }


    @Test
    void 상품조회_상품정보확인() {
        Category category = categoryRepository.save(Category.builder().name("카테고리1").build());

        Product product = productRepository.save(Product.builder()
                .name("상품1")
                .code("AEOAK001")
                .category(category)
                .description("상품설명")
                .status(ProductStatus.NEW)
                .originPrice(120000L)
                .refundInfo("환불정보")
                .sellerInfo("판매자정보")
                .sizeInfo("사이즈정보")
                .sellPrice(100000L)
                .savePoint(1000L)
                .sizeInfo("사이즈정보").build());
        Product findproduct = productRepository.findById(product.getId()).get();
        assertThat(findproduct.getCategory().getName()).isEqualTo("카테고리1");
    }

    @Test
    void 상품목록조회_상품정보확인() {

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0, 31).boxed().forEach(integer -> {
            productRepository.save(Product.builder()
                    .name("상품".concat(String.valueOf(integer)))
                    .code("AEOAK001")
                    .category(integer % 2 == 0 ? category1 : category2)
                    .description("상품설명")
                    .originPrice(120000L)
                    .refundInfo("환불정보")
                    .sellerInfo("판매자정보")
                    .status(ProductStatus.NEW)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1, 5, "id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품26");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
    }

    @Test
    void 상품목록조회_카테고리필터추가_상품정보확인() {

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0, 31).boxed().forEach(integer -> {
            productRepository.save(Product.builder()
                    .name("상품".concat(String.valueOf(integer)))
                    .code("AEOAK001")
                    .category(integer % 2 == 0 ? category1 : category2)
                    .description("상품설명")
                    .originPrice(120000L)
                    .refundInfo("환불정보")
                    .sellerInfo("판매자정보")
                    .status(ProductStatus.NEW)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category1.getId()).build(), getPage(1, 5, "id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품22");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category1.getId());
    }

    @Test
    void 상품목록조회_상태값추가_상품정보확인() {

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0, 31).boxed().forEach(integer -> {
            productRepository.save(Product.builder()
                    .name("상품".concat(String.valueOf(integer)))
                    .code("AEOAK001")
                    .category(integer % 2 == 0 ? category1 : category2)
                    .description("상품설명")
                    .originPrice(120000L)
                    .refundInfo("환불정보")
                    .sellerInfo("판매자정보")
                    .status(integer % 2 == 0 ? ProductStatus.NEW : ProductStatus.SELL)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });


        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category2.getId()).status(ProductStatus.SELL).build(), getPage(1, 5, "id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(0).getStatus()).isEqualTo(ProductStatus.SELL);
        assertThat(products.get(1).getStatus()).isEqualTo(ProductStatus.SELL);
    }

    @Test
    void 상품목록조회_정렬기준추가_상품정보확인() {

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0, 31).boxed().forEach(integer -> {
            productRepository.save(Product.builder()
                    .name("상품".concat(String.valueOf(integer)))
                    .code("AEOAK001")
                    .category(integer % 2 == 0 ? category1 : category2)
                    .description("상품설명")
                    .originPrice(120000L)
                    .refundInfo("환불정보")
                    .sellerInfo("판매자정보")
                    .status(integer % 2 == 0 ? ProductStatus.NEW : ProductStatus.SELL)
                    .sizeInfo("사이즈정보")
                    .sellPrice(100000L)
                    .savePoint(1000L)
                    .sizeInfo("사이즈정보").build());

        });

        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1, 5, "id"));
        assertThat(products.size()).isEqualTo(5L);
    }


    public Pageable getPage(int pageNumber, int pageSize, String sort) {
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return (pageNumber - 1) * pageSize;
            }

            @Override
            public Sort getSort() {
                return Sort.by(sort).descending();

            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }


}
