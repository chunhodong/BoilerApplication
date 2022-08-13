package com.bronze.boiler.filter;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasePage {


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
