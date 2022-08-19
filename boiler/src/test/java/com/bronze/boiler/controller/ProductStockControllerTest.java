package com.bronze.boiler.controller;


import com.bronze.boiler.domain.product.dto.ProductStockDto;
import com.bronze.boiler.domain.product.enums.ProductStockExceptionType;
import com.bronze.boiler.exception.ProductStockException;
import com.bronze.boiler.service.ProductStockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductStockController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ProductStockControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductStockService productStockService;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 재고추가_현재재고음수면_예외발생() throws Exception {


        mockMvc.perform(post("/pstocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ProductStockDto.builder()
                                .currentStock(-1L)
                                .totalStock(12L)
                                .productId(12L)
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_PARAM"))
                .andExpect(jsonPath("$.message").value("현재수량은 0이상이어야합니다"))
                .andDo(print());
    }

    @Test
    void 재고추가_전체재고음수면_예외발생() throws Exception {


        mockMvc.perform(post("/pstocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ProductStockDto.builder()
                                .currentStock(1L)
                                .totalStock(-12L)
                                .productId(12L)
                                .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("INVALID_PARAM"))
                .andExpect(jsonPath("$.message").value("전체수량은 0이상이어야합니다"))
                .andDo(print());
    }

    @Test
    void 재고추가_응답확인() throws Exception {
        doNothing().when(productStockService).createProductStock(any(ProductStockDto.class));

        mockMvc.perform(post("/pstocks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ProductStockDto.builder()
                                .currentStock(121L)
                                .totalStock(122L)
                                .productId(12L)
                                .build())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 재고현재수량1증가_재고엔티티없으면_예외발생() throws Exception {


        doThrow(new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT))
                .when(productStockService)
                .plusCurrentStock(anyLong());

        mockMvc.perform(put("/pstocks/11/plus-cstock"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("NONE_EXIST_PRODUCT"))
                .andExpect(jsonPath("$.message").value("존재하지않는 재고입니다"))
                .andDo(print());
    }


    @Test
    void 재고현재수량1증가_응답확인() throws Exception {
        doNothing().when(productStockService).plusCurrentStock(anyLong());

        mockMvc.perform(put("/pstocks/11/plus-cstock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void 재고현재수량1감소_재고엔티티없으면_예외발생() throws Exception {


        doThrow(new ProductStockException(ProductStockExceptionType.NONE_EXIST_PRODUCT))
                .when(productStockService)
                .minusCurrentStock(anyLong());

        mockMvc.perform(put("/pstocks/11/minus-cstock"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("NONE_EXIST_PRODUCT"))
                .andExpect(jsonPath("$.message").value("존재하지않는 재고입니다"))
                .andDo(print());
    }


    @Test
    void 재고현재수량1감소_응답확인() throws Exception {
        doNothing().when(productStockService).minusCurrentStock(anyLong());

        mockMvc.perform(put("/pstocks/11/minus-cstock")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private <T> String toJson(T data) throws JsonProcessingException {
        return objectMapper.writeValueAsString(data);
    }


}
