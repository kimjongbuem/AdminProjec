package com.study.study.model.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemStatus {
    REGISTERED(1,"등록", "아이템등록상태"),
    UNREGISTERED(2, "해지", "아이템해지상태"),
    WAITING(3, "대기", "아이템대기상태");

    private final Integer id;
    private final String title;
    private final String description;
}
