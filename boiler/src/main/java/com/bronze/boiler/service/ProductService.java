package com.bronze.boiler.service;


import com.bronze.boiler.domain.product.converter.ProductConverter;
import com.bronze.boiler.domain.product.dto.ReqProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.filter.Page;
import com.bronze.boiler.repository.ProductImageRepository;
import com.bronze.boiler.repository.ProductOptionRepository;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.util.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductOptionRepository productOptionRepository;
    /**
     * 상품dto DB에 저장
     * @param reqProductDto 상품dto
     * @return 저장된상품dto
     */
    public ReqProductDto createProduct(ReqProductDto reqProductDto) {

        Product product = productRepository.save(ProductConverter.toProduct(reqProductDto));
        return ProductConverter.toProductDto(product);
    }

    /**
     * 상품데이터조회
     * @param productId 상품아이디
     * @return 상품상세데이터
     */
    public ReqProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        List<ProductOption> productOptions = productOptionRepository.findAllByProduct(product);
        return ProductConverter.toProductDto(product,productImages,productOptions);
    }

    /**
     * 상품목록조회
     * @param page 상품페이지
     * @return 상품목록데이터
     */
    public Response<ReqProductDto> getProducts(Page page) {

        Long count = productRepository.count();
        List<Product> products = productRepository.findAllByPage(page);
        List<ProductImage> productImages = productImageRepository.findAllByProductIn(products);

        return Response.<ReqProductDto>builder()
                .total(count)
                .currentPage(page.getPageNum())
                .list(products
                        .stream()
                        .map(product -> ProductConverter.toProductDto(product, productImages
                                        .stream()
                                        .filter(productImage -> productImage.getProduct().equals(product))
                                        .collect(Collectors.toList()),null))
                        .collect(Collectors.toList())).build();

    }

    /**
     * 상품삭제
     * @param productId 상품아이디
     * @return 삭제상태상품dto
     */
    public void closeProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        product.close();
    }


    /**
     * 상품원가격수정
     * @param productId 상품아이디
     * @param price 상품가격
     */
    public void modifyProductOriginprice(Long productId,Long price){

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        if(price <= 0)throw new ProductException(ProductExceptionType.ILLEGAL_NEGATIVE_PRICE);
        product.modifyOriginprice(price);
    }

    /**
     * 상품판매가격수정
     * @param productId 상품아이디
     * @param price 상품가격
     */
    public void modifyProductSellprice(Long productId, Long price) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        if(price <= 0)throw new ProductException(ProductExceptionType.ILLEGAL_NEGATIVE_PRICE);
        product.modifySellprice(price);

    }

    //상품목록조회필터적용
    //상품수정
    //상품단순조회(이미지,옵션X)
}
