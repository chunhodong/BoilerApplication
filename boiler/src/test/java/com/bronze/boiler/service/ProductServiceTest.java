package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.enums.OptionType;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.filter.Page;
import com.bronze.boiler.repository.ProductImageRepository;
import com.bronze.boiler.repository.ProductOptionRepository;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.util.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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

    private final ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

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
        ProductDto productDto = productService.createProduct(ProductDto.builder()
                .build());

        assertThat(productDto.getId()).isEqualTo(1L);
        assertThat(productDto.getName()).isEqualTo("상품1");
        assertThat(productDto.getCode()).isEqualTo("001XD3");
        assertThat(productDto.getDescription()).isEqualTo("상품설명");
        assertThat(productDto.getCategory()).isEqualTo(category);

    }


    @Test
    void 상품등록_DTO를엔티티로변환후_입력값확인() {
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

        productService.createProduct(ProductDto.builder()
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
                .build());

        verify(productRepository).save(captor.capture());
        assertThat(captor.getValue().getCategory()).isEqualTo(category);
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
        doReturn(Optional.ofNullable(Product.builder()
                .status(ProductStatus.SELL)
                .build())).when(productRepository).findById(any());
        ProductDto productDto = productService.closeProduct(1L);
        assertThat(productDto.getStatus()).isEqualTo(ProductStatus.CLOSE);
    }

    @Test
    void 상품조회_상품확인() {
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


        ProductDto productDto = productService.getProduct(1L);

        assertThat(productDto.getId()).isEqualTo(1L);
        assertThat(productDto.getName()).isEqualTo("상품1");
        assertThat(productDto.getCode()).isEqualTo("001XD3");
        assertThat(productDto.getDescription()).isEqualTo("상품설명");
        assertThat(productDto.getCategory()).isEqualTo(category);
        assertThat(productDto.getSellerInfo()).isEqualTo("판매자정보");
        assertThat(productDto.getImageUrls())
                .isEqualTo(List.of("도메인1/도메인경로1", "도메인2/도메인경로2"));
        assertThat(productDto.getOptions()).extracting("type", "value")
                .contains(tuple(OptionType.COLOR, "blue"), tuple(OptionType.SIZE, "260"));
    }


    @Test
    void 상품목록조회_상품확인() {
        doReturn(10L).when(productRepository).count();
        doReturn(List.of(Product
                        .builder()
                        .id(1L)
                        .name("상품1")
                        .originPrice(1000L)

                        .build(),
                Product.builder()
                        .id(2L)
                        .name("상품2")
                        .originPrice(2000L)
                        .build()))
                .when(productRepository).findAllByPage(any());

        doReturn(List.of(ProductImage.builder().domain("domain1").path("/path1").product(Product.builder().id(1L).build()).build(),
                ProductImage.builder().domain("domain2").path("/path2").product(Product.builder().id(2L).build()).build()))
                .when(productImageRepository).findAllByProductIn(any());

        Response<ProductDto> response = productService.getProducts(Page.builder().pageNum(1L).build());
        assertThat(response.getList()).extracting("name", "originPrice","imageUrls")
                .contains(tuple("상품1", 1000L,List.of("domain1/path1")),tuple("상품2",2000L,List.of("domain2/path2")));
        assertThat(response.getTotal()).isEqualTo(10);
        assertThat(response.getCurrentPage()).isEqualTo(1);
    }

}
