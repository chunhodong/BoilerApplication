package com.bronze.boiler.repository;

import com.bronze.boiler.config.TestConfig;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.filter.ProductFilter;
import com.bronze.boiler.utils.RandomGenerator;
import com.mysql.cj.xdevapi.Table;
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
    HikariDataSource hikariDataSource;


    private int batchSize = 20000;

    @Test
    void 상품추가배치(){

    }
    @Test
    void 상품추가() {
        Random random = new Random();


        //패션 > 신발
        //패션 > 상의
        //패션 > 하의
        //패션 > 속옷
        //가구 > 책상
        //가구 > 의자
        //기타
        //인테리어 > 침구 > 베게
        //인테리어 > 침구 > 매트
        //인테리어 > 침구 > 이불
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

        List<Category> categories = List.of(category1,category2,category3,category4,
                category5,category6,parentCategory3,category8,category9,
                category10);

        Category category = categories.get(random.nextInt(10));


        List<Product> products = new ArrayList<>();
        int count = 1000000;
        for(int i = 0; i < count; i++){
            products.add(RandomGenerator.getProduct(category));

        }

        saveAllJdbcBatch(products);
        //productRepository.saveAll(products);


    }



    public void saveAllJdbcBatch(List<Product> productData){
        System.out.println("insert using jdbc batch");
        String sql =
                "INSERT INTO product (code, description, has_option,name,origin_price,refund_info,save_point,sell_price,seller_info,size_info,status) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection connection = hikariDataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)
        ){
            int counter = 0;
            for (Product product : productData) {
                statement.clearParameters();
                statement.setString(1, product.getCode());
                statement.setString(2, product.getDescription());
                statement.setBoolean(3, product.isHasOption());
                statement.setString(4,product.getName());

                statement.setLong(5,product.getOriginPrice());
                statement.setString(6, product.getRefundInfo());
                statement.setLong(7, product.getSavePoint());
                statement.setLong(8, product.getSellPrice());
                statement.setString(9, product.getSellerInfo());
                statement.setString(10, product.getSizeInfo());
                statement.setString(11, product.getStatus().name());
                //statement.setLong(12, product.getCategory().getId());

                statement.addBatch();
                System.out.println("count : "+counter+" p size : "+productData.size());


                if ((counter + 1) % batchSize == 0 || (counter + 1) == productData.size()) {
                    System.out.println("wow!!");
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
    void 상품조회_상품정보확인(){
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
    void 상품목록조회_상품정보확인(){

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0,31).boxed().forEach(integer -> {
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
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품26");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
    }

    @Test
    void 상품목록조회_카테고리필터추가_상품정보확인(){

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0,31).boxed().forEach(integer -> {
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
        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category1.getId()).build(),getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(4).getName()).isEqualTo("상품22");
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category1.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category1.getId());
    }

    @Test
    void 상품목록조회_상태값추가_상품정보확인(){

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0,31).boxed().forEach(integer -> {
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


        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().categoryId(category2.getId()).status(ProductStatus.SELL).build(),getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
        assertThat(products.get(0).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(1).getCategory().getId()).isEqualTo(category2.getId());
        assertThat(products.get(0).getStatus()).isEqualTo(ProductStatus.SELL);
        assertThat(products.get(1).getStatus()).isEqualTo(ProductStatus.SELL);
    }

    @Test
    void 상품목록조회_정렬기준추가_상품정보확인(){

        Category category1 = categoryRepository.save(Category.builder().name("카테고리1").build());
        Category category2 = categoryRepository.save(Category.builder().name("카테고리2").build());

        IntStream.range(0,31).boxed().forEach(integer -> {
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

        List<Product> products = productRepository.findAllByPage(ProductFilter.builder().build(), getPage(1,5,"id"));
        assertThat(products.size()).isEqualTo(5L);
    }



    public Pageable getPage(int pageNumber, int pageSize, String sort){
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
