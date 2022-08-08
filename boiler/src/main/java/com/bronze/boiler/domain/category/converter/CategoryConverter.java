package com.bronze.boiler.domain.category.converter;

import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;
import com.bronze.boiler.domain.product.dto.ProductOptionDto;
import com.bronze.boiler.domain.product.dto.ReqProductDto;
import com.bronze.boiler.domain.product.entity.Product;
import com.bronze.boiler.domain.product.entity.ProductImage;
import com.bronze.boiler.domain.product.entity.ProductOption;
import org.thymeleaf.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

   public static CategoryDto toCategoryDto(Category category){
       Category parent = category.getParent();
       return CategoryDto.builder()
               .id(category.getId())
               .name(category.getName())
               .parent(parent == null ? null : CategoryDto.builder().id(parent.getId()).name(parent.getName()).build())
               .build();
   }

    public static Category toCategory(CategoryDto categoryDto){
       CategoryDto parentDto = categoryDto.getParent();
        return Category.builder()
                .name(categoryDto.getName())
                .parent(parentDto == null ? null : Category.builder().name(parentDto.getName()).build())
                .build();
    }


}
