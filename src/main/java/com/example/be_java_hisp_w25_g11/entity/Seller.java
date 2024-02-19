package com.example.be_java_hisp_w25_g11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller extends User {
    private Set<User> followers;
    private Set<SellerPost> posts;
    private Set<Seller> followed;
}