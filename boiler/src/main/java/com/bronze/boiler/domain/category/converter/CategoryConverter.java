package com.bronze.boiler.domain.category.converter;

import com.bronze.boiler.domain.category.dto.CategoryDto;
import com.bronze.boiler.domain.category.entity.Category;

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
