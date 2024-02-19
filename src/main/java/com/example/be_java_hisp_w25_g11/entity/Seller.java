package com.example.be_java_hisp_w25_g11.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private Long id;
    private String name;
    private Set<Long> followers;
    private Set<Long> followed;
    private Set<SellerPost> posts;

    public Seller(Long id, String name) {
        this.id = id;
        this.name = name;
        this.followers = new HashSet<>();
        this.posts = new HashSet<>();
        this.followed = new HashSet<>();
    }
}