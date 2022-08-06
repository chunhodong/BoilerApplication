package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.member.enums.MemberExceptionType;
import com.bronze.boiler.domain.member.enums.MemberStatus;
import com.bronze.boiler.domain.member.enums.Role;
import com.bronze.boiler.domain.member.exception.MemberException;
import com.bronze.boiler.domain.product.dto.ProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.enums.ProductStatus;
import com.bronze.boiler.repository.MemberRepository;
import com.bronze.boiler.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

    @Test
    void 상품등록_상품확인(){
        Category category = Category.builder().name("카테고리1").build();
        doReturn(Product.builder()
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
        assertThat(productDto.getName()).isEqualTo("상품1");
        assertThat(productDto.getCode()).isEqualTo("001XD3");
        assertThat(productDto.getDescription()).isEqualTo("상품설명");
        assertThat(productDto.getCategory()).isEqualTo(category);




    }






}
