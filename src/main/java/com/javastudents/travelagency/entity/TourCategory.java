package com.javastudents.travelagency.entity;

import lombok.*;

@Builder
@ToString
@Getter @Setter
@EqualsAndHashCode
public class TourCategory implements Entity {
    private Integer id;
    private String name;
}