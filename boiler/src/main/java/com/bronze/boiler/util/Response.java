package com.bronze.boiler.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
public class Response<T> {

    private int total;
    private int currentPage;

    private List<T> list;



}
