package com.study.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderGroupStatus {
    REGISTERED(1,"등록", "그룹등록상태"),
    UNREGISTERED(2, "해지", "그룹해지상태"),
    WAITING(3, "대기", "그룹대기상태");

    private final Integer id;
    private final String title;
    private final String description;
}
