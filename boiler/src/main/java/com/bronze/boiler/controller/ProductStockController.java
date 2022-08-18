package com.bronze.boiler.controller;

import com.bronze.boiler.domain.member.dto.ReqMemberDto;
import com.bronze.boiler.domain.member.dto.ResMemberDto;
import com.bronze.boiler.domain.product.dto.ProductStockDto;
import com.bronze.boiler.service.ProductService;
import com.bronze.boiler.service.ProductStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pstocks")
public class ProductStockController {




    private final ProductStockService productStockService;

    @PostMapping
    public ResponseEntity<ResMemberDto> createStocks(@RequestBody @Valid ProductStockDto stockDto)  {
        productStockService.createProductStock(stockDto);
        return new ResponseEntity(HttpStatus.OK);

    }
}
