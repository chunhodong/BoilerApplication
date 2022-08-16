package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.product.dto.*;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.*;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.exception.ProductReviewException;
import com.bronze.boiler.filter.ProductFilter;
import com.bronze.boiler.filter.ProductReviewFilter;
import com.bronze.boiler.repository.ProductImageRepository;
import com.bronze.boiler.repository.ProductOptionRepository;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.repository.ProductReviewRepository;
import com.bronze.boiler.util.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//테스트실행 확장을 위해 추가하는 Annotation
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {


    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductImageRepository productImageRepository;

    @Mock
    private ProductOptionRepository productOptionRepository;

    @Mock
    private ProductReviewRepository productReviewRepository;


    @Test
    void 상품등록_상품확인() {

        Category category = Category.builder().name("카테고리1").build();
        doReturn(Product.builder()
                .id(1L)
                .name("상품1")
                .code("001XD3")
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .description("상품설명")
                .savePoint(1200L)
                .sellPrice(13000L)
                .originPrice(15000L)
                .category(category)
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .build()).when(productRepository).save(any());
        ResProductDetailDto resProductDetailDto = productService.createProduct(ReqProductDto.builder()
                .id(1L)
                .name("상품1")
                .code("001XD3")
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .description("상품설명")
                .savePoint(1200L)
                .sellPrice(13000L)
                .originPrice(15000L)
                .category(CategoryDto.builder().name("카테고리1").build())
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .build());

        assertThat(resProductDetailDto.getId()).isEqualTo(1L);
        assertThat(resProductDetailDto.getName()).isEqualTo("상품1");
        assertThat(resProductDetailDto.getCode()).isEqualTo("001XD3");
        assertThat(resProductDetailDto.getDescription()).isEqualTo("상품설명");
        assertThat(resProductDetailDto.getCategory().getName()).isEqualTo("카테고리1");

    }


    @Test
    void 상품등록_DTO를엔티티로변환후_입력값확인() {
        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        Category category = Category.builder().id(15L).name("카테고리1").build();
        doReturn(Product.builder()
                .id(1L)
                .name("상품1")
                .code("001XD3")
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .description("상품설명")
                .savePoint(1200L)
                .sellPrice(13000L)
                .originPrice(15000L)
                .category(category)
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .build()).when(productRepository).save(any());

        productService.createProduct(ReqProductDto.builder()
                .name("상품1")
                .code("001XD3")
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .description("상품설명")
                .savePoint(1200L)
                .sellPrice(13000L)
                .originPrice(15000L)
                .category(CategoryDto.builder().id(15L).name("카테고리1").build())
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .build());

        verify(productRepository).save(captor.capture());
        assertThat(captor.getValue().getCategory().getName()).isEqualTo(category.getName());
        assertThat(captor.getValue().getName()).isEqualTo("상품1");
        assertThat(captor.getValue().getCode()).isEqualTo("001XD3");
        assertThat(captor.getValue().getStatus()).isEqualTo(ProductStatus.NEW);


    }


    @Test
    void 상품종료_없는상품인경우_예외발생() {
        doReturn(Optional.empty()).when(productRepository).findById(any());
        ProductException productException = assertThrows(ProductException.class, () -> productService.closeProduct(12L));
        assertThat(productException.getType()).isEqualTo(ProductExceptionType.NONE_EXIST_PRODUCT);

    }

    @Test
    void 상품종료_종료상품확인() {
        Product product = Product.builder()
                .status(ProductStatus.SELL)
                .build();
        doReturn(Optional.ofNullable(product)).when(productRepository).findById(any());
        productService.closeProduct(1L);
        assertThat(product.getStatus()).isEqualTo(ProductStatus.CLOSE);
    }

    @Test
    void 상품상세조회_상품확인() {
        Category category = Category.builder().name("카테고리1").build();

        doReturn(Optional.ofNullable(Product.builder()
                .id(1L)
                .name("상품1")
                .code("001XD3")
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .description("상품설명")
                .savePoint(1200L)
                .sellPrice(13000L)
                .originPrice(15000L)
                .category(category)
                .status(ProductStatus.NEW)
                .sizeInfo("사이즈정보")
                .build())).when(productRepository).findById(any());

        doReturn(List.of(
                ProductImage.builder()
                        .domain("도메인1")
                        .path("/도메인경로1")
                        .build(),
                ProductImage.builder()
                        .domain("도메인2")
                        .path("/도메인경로2")
                        .build()))
                .when(productImageRepository).findAllByProduct(any());


        doReturn(List.of(
                ProductOption.builder()
                        .id(1L)
                        .type(OptionType.SIZE)
                        .value("260")
                        .build(),
                ProductOption.builder()
                        .type(OptionType.COLOR)
                        .value("blue")
                        .build()))
                .when(productOptionRepository).findAllByProduct(any());


        ResProductDetailDto reqProductDto = productService.getProduct(1L);

        assertThat(reqProductDto.getId()).isEqualTo(1L);
        assertThat(reqProductDto.getName()).isEqualTo("상품1");
        assertThat(reqProductDto.getCode()).isEqualTo("001XD3");
        assertThat(reqProductDto.getDescription()).isEqualTo("상품설명");
        assertThat(reqProductDto.getCategory().getName()).isEqualTo(category.getName());
        assertThat(reqProductDto.getSellerInfo()).isEqualTo("판매자정보");
        assertThat(reqProductDto.getImageUrls())
                .isEqualTo(List.of("도메인1/도메인경로1", "도메인2/도메인경로2"));
        assertThat(reqProductDto.getOptions()).extracting("type", "value")
                .contains(tuple(OptionType.COLOR, "blue"), tuple(OptionType.SIZE, "260"));
    }


    @Test
    void 상품상세목록조회_상품확인() {
        doReturn(10L).when(productRepository).count();
        doReturn(List.of(Product
                        .builder()
                        .id(1L)
                        .category(Category.builder().id(1L).name("스니커즈").build())
                        .name("상품1")
                        .originPrice(1000L)
                        .build(),
                Product.builder()
                        .id(2L)
                        .category(Category.builder().id(2L).name("셔츠").build())
                        .name("상품2")
                        .originPrice(2000L)
                        .build()))
                .when(productRepository).findAllByPage(any(),any());

        doReturn(List.of(ProductImage.builder().domain("domain1").path("/path1").product(Product.builder().id(1L).build()).build(),
                ProductImage.builder().domain("domain2").path("/path2").product(Product.builder().id(2L).build()).build()))
                .when(productImageRepository).findAllByProductIn(any());


        Response<ResProductDetailDto> response = productService
                .getDetailProducts(ProductFilter.builder().build(), getPage(1,10,"id"));
        assertThat(response.getList()).extracting("name", "originPrice", "imageUrls")
                .contains(tuple("상품1", 1000L, List.of("domain1/path1")), tuple("상품2", 2000L, List.of("domain2/path2")));
        assertThat(response.getTotal()).isEqualTo(10);
        assertThat(response.getCurrentPage()).isEqualTo(1);
    }

    @Test
    void 상품목록조회_상품확인() {
        doReturn(10L).when(productRepository).count();
        doReturn(List.of(Product
                        .builder()
                        .id(1L)
                        .category(Category.builder().id(1L).name("스니커즈").build())
                        .name("상품1")
                        .originPrice(1000L)
                        .build(),
                Product.builder()
                        .id(2L)
                        .category(Category.builder().id(2L).name("셔츠").build())
                        .name("상품2")
                        .originPrice(2000L)
                        .build()))
                .when(productRepository).findAllByPage(any(),any());


        doReturn(List.of(ProductImage.builder().domain("domain1").path("/path1").product(Product.builder().id(1L).build()).build(),
                ProductImage.builder().domain("domain2").path("/path2").product(Product.builder().id(2L).build()).build()))
                .when(productImageRepository).findAllByProductIn(any());


        Response<ResProductDto> response = productService.getProducts(ProductFilter.builder().build(), getPage(1,10,"id"));
        assertThat(response.getList()).extracting("name", "originPrice", "imageUrls")
                .contains(tuple("상품1", 1000L, List.of("domain1/path1")), tuple("상품2", 2000L, List.of("domain2/path2")));
        assertThat(response.getTotal()).isEqualTo(10);
        assertThat(response.getCurrentPage()).isEqualTo(1);
    }


    @Test
    void 상품원가격수정_가격이음수면_예외발생() {
        doReturn(Optional.ofNullable(Product.builder().originPrice(10000L).sellPrice(9000L).build())).when(productRepository).findById(any());
        ProductException productException = assertThrows(ProductException.class, () -> productService.modifyProductOriginprice(1L, -1L));
        assertThat(productException.getType()).isEqualTo(ProductExceptionType.ILLEGAL_NEGATIVE_PRICE);
    }

    @Test
    void 상품원가격수정_상품가격확인() {
        Product product = Product.builder().originPrice(10000L).sellPrice(9000L).build();
        doReturn(Optional.ofNullable(product)).when(productRepository).findById(any());
        productService.modifyProductOriginprice(13L, 4000L);
        assertThat(product.getOriginPrice()).isEqualTo(4000L);
    }

    @Test
    void 상품판매가격수정_가격이음수면_예외발생() {
        doReturn(Optional.ofNullable(Product.builder().originPrice(10000L).sellPrice(9000L).build())).when(productRepository).findById(any());
        ProductException productException = assertThrows(ProductException.class, () -> productService.modifyProductSellprice(1L, -1L));
        assertThat(productException.getType()).isEqualTo(ProductExceptionType.ILLEGAL_NEGATIVE_PRICE);

    }

    @Test
    void 상품판매가격수정_상품가격확인() {
        Product product = Product.builder().originPrice(10000L).sellPrice(9000L).build();
        doReturn(Optional.ofNullable(product)).when(productRepository).findById(any());
        productService.modifyProductOriginprice(13L, 7000L);
        assertThat(product.getOriginPrice()).isEqualTo(7000L);
    }

    @Test
    void 상품수정_입력값확인() {

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        productService.modifyProduct(ReqProductDto
                .builder()
                .id(1L)
                .name("상품1")
                .code("OEVMW11X")
                .description("상품설명")
                .category(CategoryDto.builder().id(1L).name("카테고리").build())
                .originPrice(13000L)
                .sellerInfo("판매자정보")
                .refundInfo("환불정보")
                .sellPrice(12000L)
                .savePoint(100L)
                .status(ProductStatus.SELL)
                .build());

        verify(productRepository).save(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("상품1");
        assertThat(captor.getValue().getCode()).isEqualTo("OEVMW11X");
        assertThat(captor.getValue().getDescription()).isEqualTo("상품설명");
        assertThat(captor.getValue().getOriginPrice()).isEqualTo(13000L);
        assertThat(captor.getValue().getSellPrice()).isEqualTo(12000L);

    }


    @Test
    void 댓글추가_댓글확인() {
        ArgumentCaptor<ProductReview> captor = ArgumentCaptor.forClass(ProductReview.class);

        ReqProductReviewDto reqProductReviewDto = ReqProductReviewDto.builder()
                .text("대댓글")
                .member(ReqMemberDto.builder().id(13L).build())
                .build();

        doReturn(ProductReview
                .builder()
                .id(3L)
                .text("리뷰댓글")
                .member(Member.builder().id(13L).build())
                .build()).when(productReviewRepository).save(any());
        ResProductReviewDto resProductReviewDto = productService.createProductReview(reqProductReviewDto);
        verify(productReviewRepository).save(captor.capture());
        assertThat(resProductReviewDto.getId()).isNotNull();
        assertThat(captor.getValue().getText()).isNotNull();

    }

    @Test
    void 댓글조회_댓글없으면_예외발생() {
        ProductReviewException exception = assertThrows(ProductReviewException.class, () -> productService.getProductReview(1L));
        assertThat(exception.getType()).isEqualTo(ProductReviewExceptionType.NONE_EXIST_REVIEW);
    }

    @Test
    void 댓글조회_삭제된댓글_예외발생() {
        doReturn(Optional.ofNullable(ProductReview.builder().status(ProductReviewStatus.REMOVED).build()))
                .when(productReviewRepository).findById(any());

        ProductReviewException exception = assertThrows(ProductReviewException.class, () -> productService.getProductReview(1L));
        assertThat(exception.getType()).isEqualTo(ProductReviewExceptionType.REMOVED_REVIEW);
    }


    @Test
    void 댓글조회_부모댓글미포함_댓글확인() {
        doReturn(Optional.ofNullable(ProductReview.builder()
                .text("댓글")
                .member(Member.builder().id(13L).build())
                .product(Product.builder().id(4L).build())
                .build()))
                .when(productReviewRepository)
                .findById(any());
        ResProductReviewDto productReviewDto = productService.getProductReview(1L);
        assertThat(productReviewDto.getText()).isEqualTo("댓글");
        assertThat(productReviewDto.getMember().getId()).isEqualTo(13L);
        assertThat(productReviewDto.getParent()).isNull();
    }

    @Test
    void 댓글조회_부모댓글포함_댓글확인() {
        doReturn(Optional.ofNullable(ProductReview.builder()
                .text("댓글")
                .member(Member.builder().id(13L).build())
                .product(Product.builder().id(4L).build())
                .parent(ProductReview.builder()
                        .text("댓글")
                        .member(Member.builder().id(13L).build())
                        .product(Product.builder().id(4L).build())
                        .build())
                .build()))
                .when(productReviewRepository)
                .findById(any());
        ResProductReviewDto productReviewDto = productService.getProductReview(1L);
        assertThat(productReviewDto.getText()).isEqualTo("댓글");
        assertThat(productReviewDto.getMember().getId()).isEqualTo(13L);
        assertThat(productReviewDto.getParent()).isNotNull();
    }

    @Test
    void 댓글목록조회_댓글목록확인() {


        doReturn(List.of(ProductReview.builder()
                        .id(1L)
                        .text("댓글1")
                        .status(ProductReviewStatus.NEW)
                        .product(Product.builder().id(3L).build())
                        .member(Member.builder().id(1L).build())
                        .build(),
                ProductReview.builder()
                        .id(2L)
                        .text("댓글2")
                        .status(ProductReviewStatus.NEW)
                        .product(Product.builder().id(3L).build())
                        .member(Member.builder().id(2L).build())
                        .build(),
                ProductReview.builder()
                        .id(3L)
                        .text("댓글3")
                        .status(ProductReviewStatus.NEW)
                        .product(Product.builder().id(3L).build())
                        .member(Member.builder().id(3L).build())
                        .build()))

                .when(productReviewRepository).findAllByPage(any(), any());
        doReturn(3L).when(productReviewRepository).count();

        Response<ResProductReviewDto> productReviewDtoResponse = productService.getProductReviews(ProductReviewFilter.builder().build(), getPage(1, 10, "id"));

        assertThat(productReviewDtoResponse.getTotal()).isEqualTo(3L);
        assertThat(productReviewDtoResponse.getList().get(0).getId()).isEqualTo(1L);

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
