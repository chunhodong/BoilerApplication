package com.bronze.boiler.service;

import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.product.dto.ProductStockDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductStock;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.domain.product.enums.ProductStockExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.exception.ProductStockException;
import com.bronze.boiler.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//테스트실행 확장을 위해 추가하는 Annotation
@ExtendWith(SpringExtension.class)
public class ProductStockServiceTest {


    @InjectMocks
    private ProductStockService productStockService;

    @Mock
    private ProductStockRepository productStockRepository;


    @Mock
    private ProductRepository productRepository;



    @Test
    void 재고수량초기화_입고량이현재수량보다작으면_예외발생(){

        ProductStockException productStockException = assertThrows(ProductStockException.class,() -> productStockService.createProductStock(ProductStockDto.builder().productId(1l).currentStock(44l).totalStock(12l).build()));
        assertThat(productStockException.getType()).isEqualTo(ProductStockExceptionType.ILLEGAL_STOCK);

    }

    @Test
    void 재고수량초기화_상품이없으면_예외발생(){

        doReturn(Optional.empty()).when(productRepository).findById(anyLong());

        ProductException productException = assertThrows(ProductException.class,() -> productStockService.createProductStock(ProductStockDto.builder().build()));
        assertThat(productException.getType()).isEqualTo(ProductExceptionType.NONE_EXIST_PRODUCT);

    }


    @Test
    void 재고수량초기화(){

        doReturn(Optional.ofNullable(Product.builder().build())).when(productRepository).findById(anyLong());
        ArgumentCaptor<ProductStock> captor = ArgumentCaptor.forClass(ProductStock.class);
        productStockService.createProductStock(ProductStockDto.builder().productId(33l).totalStock(100l).currentStock(100l).build());
        verify(productStockRepository).save(captor.capture());

        assertThat(captor.getValue().getProduct()).isNotNull();
        assertThat(captor.getValue().getTotalStock()).isEqualTo(100l);
        assertThat(captor.getValue().getCurrentStock()).isEqualTo(100l);

    }


    @Test
    void 재고현재수량수정_현재수량이전체수량보다많으면_예외발생(){
        doReturn(ProductStock.builder()
                .totalStock(100l)
                .currentStock(90l)
                .build()).when(productStockRepository).findById(anyLong());

        assertThrows(ProductStockException.class,() -> productStockService.modifyCurrentStock(1l,113l));

    }




}
