package com.example.nuonuo.util;

import com.example.nuonuo.pojo.dto.PageDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PageHelper {
    private static final Integer PAGE = 1;

    private final Integer defaultPageSize;

    private final String defaultOrder;

    public PageHelper(@Value("${pagehelper.default-page-size}") Integer defaultPageSize,
                      @Value("${pagehelper.default-order}") String defaultOrder) {
        this.defaultPageSize = defaultPageSize;
        this.defaultOrder = defaultOrder;
    }

    public void startPage(PageDTO pageDTO) {
        startPage(pageDTO, false);
    }

    public void startPage(PageDTO pageDTO, boolean required) {
        if (pageDTO.getPage() == null && required) {
            pageDTO.setPage(PAGE);
        }
        if (pageDTO.getPage() != null) {
            if (pageDTO.getPageSize() == null) {
                pageDTO.setPageSize(defaultPageSize);
            }
            if (pageDTO.getSortBy() == null) {
                com.github.pagehelper.PageHelper.startPage(pageDTO.getPage(), pageDTO.getPageSize());
            } else {
                if (pageDTO.getOrder() == null) {
                    pageDTO.setOrder(defaultOrder);
                }
                com.github.pagehelper.PageHelper.startPage(pageDTO.getPage(), pageDTO.getPageSize(), pageDTO.getSortBy() + " " + pageDTO.getOrder());
            }
        }
    }

}
