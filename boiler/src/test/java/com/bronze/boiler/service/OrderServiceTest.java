package com.bronze.boiler.service;

import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.entity.Member;
import com.bronze.boiler.domain.order.dto.ReqOrderDto;
import com.bronze.boiler.domain.order.dto.ResOrderDto;
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
import com.bronze.boiler.repository.*;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

//테스트실행 확장을 위해 추가하는 Annotation
@ExtendWith(SpringExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void 주문추가_주문확인(){
        ReqOrderDto reqOrderDto = ReqOrderDto.builder().build();
        ResOrderDto orderDto = orderService.createOrder(reqOrderDto);

    }

}
