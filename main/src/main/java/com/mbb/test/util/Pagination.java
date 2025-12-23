package com.mbb.test.util;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pagination<T> {
    private Integer pageNo;
    private Integer totalPageSize;
    private boolean hasNext;
    private boolean hasPrevious;
    private List<T> items;
}
