package com.hediyesilaozyurt.dto.utils;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageResponse<T>{

    private List<T> content;

    private final int pageNumber;
    private final int pageSize;
    private final long totalElements;
    private final int totalPages;
    private final boolean last;

    public PageResponse(List<T> content, Page<?> page){
        this.content=content;
        this.pageNumber=page.getNumber();
        this.pageSize=page.getSize();
        this.totalElements=page.getTotalElements();
        this.totalPages=page.getTotalPages();
        this.last=page.isLast();
    }
}
