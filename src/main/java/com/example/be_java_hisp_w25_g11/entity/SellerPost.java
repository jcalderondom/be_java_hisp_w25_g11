package com.example.be_java_hisp_w25_g11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerPost {
    private Long id;
    private Date date;
    private Integer Category;
    private Double price;
    private Boolean saleItem;
    private Double discount;
    private Seller seller;
    private Product product;
}
