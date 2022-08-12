package com.bronze.boiler.service;


import com.bronze.boiler.domain.product.converter.ProductConverter;
import com.bronze.boiler.domain.product.converter.ProductReviewConverter;
import com.bronze.boiler.domain.product.dto.*;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import com.bronze.boiler.domain.product.entity.ProductReview;
import com.bronze.boiler.domain.product.enums.ProductExceptionType;
import com.bronze.boiler.exception.ProductException;
import com.bronze.boiler.filter.Page;
import com.bronze.boiler.repository.ProductImageRepository;
import com.bronze.boiler.repository.ProductOptionRepository;
import com.bronze.boiler.repository.ProductRepository;
import com.bronze.boiler.repository.ProductReviewRepository;
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
    private final ProductReviewRepository productReviewRepository;
    /**
     * 상품dto DB에 저장
     * @param reqProductDto 상품dto
     * @return 저장된상품dto
     */
    public ResProductDetailDto createProduct(ReqProductDto reqProductDto) {

        Product product = productRepository.save(ProductConverter.toProduct(reqProductDto));
        return ProductConverter.toProductDto(product);
    }

    /**
     * 상품데이터조회
     * @param productId 상품아이디
     * @return 상품상세데이터
     */
    public ResProductDetailDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductExceptionType.NONE_EXIST_PRODUCT));
        List<ProductImage> productImages = productImageRepository.findAllByProduct(product);
        List<ProductOption> productOptions = productOptionRepository.findAllByProduct(product);
        return ProductConverter.toProductDto(product,productImages,productOptions);
    }

    /**
     * 상품상세목록조회
     * @param page 상품페이지데이터
     * @return 상품상세목록데이터
     */
    public Response<ResProductDetailDto> getDetailProducts(Page page) {

        Long count = productRepository.count();
        List<Product> products = productRepository.findAllByPage(page);
        List<ProductImage> productImages = productImageRepository.findAllByProductIn(products);

        return Response.<ResProductDetailDto>builder()
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
     * 상품목록조회
     * @param page 상품페이징데이터
     * @return 상품목록데이터
     */
    public Response<ResProductDto> getProducts(Page page) {
        long count = productRepository.count();
        List<Product> products = productRepository.findAllByPage(page);
        List<ProductImage> productImages = productImageRepository.findAllByProductIn(products);

        return Response.<ResProductDto>builder()
                .total(count)
                .currentPage(page.getPageNum())
                .list(products
                        .stream()
                        .map(product -> ProductConverter.toProductDto(product, productImages
                                .stream()
                                .filter(productImage -> productImage.getProduct().equals(product))
                                .collect(Collectors.toList())))
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

    /**
     * 상품수정
     * @param reqProductDto 상품DTO
     */
    public void modifyProduct(ReqProductDto reqProductDto) {

        productRepository.save(ProductConverter.toProduct(reqProductDto));
    }

    public ResProductReviewDto createProductReview(ReqProductReviewDto reqProductReviewDto) {





        ProductReview productReview = productReviewRepository.save(ProductReviewConverter.toProductReview(reqProductReviewDto));

        return ProductReviewConverter.toProductReviewDto(productReview);
    }
}
