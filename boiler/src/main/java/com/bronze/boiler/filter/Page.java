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
    private Long pageOffset;

    public Long getPageOffset(){
        return pageNum > 0 ? ( pageNum - 1 ) * pageSize : 0;
    }

    public Long getPageSize(){
        return pageSize == null ? 10L : pageSize;
    }
}
