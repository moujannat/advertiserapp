package com.spring.projects.advertiserapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advertiser {

    private String name;
    private String contactName;
    private Double creditLimit;
}
