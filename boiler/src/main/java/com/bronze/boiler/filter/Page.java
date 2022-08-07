package com.bronze.boiler.filter;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Page {

    private Long pageNum;
    private Long pageSize;
}
