package com.example.be_java_hisp_w25_g11.repository;

import com.example.be_java_hisp_w25_g11.entity.Product;
import com.example.be_java_hisp_w25_g11.entity.SellerPost;

import java.util.List;

public interface ISellerPostRepository {
    List<SellerPost> sellerPosts = null;
    List<Product> products = null;
    List<SellerPost> getAllSellerPosts();

    Boolean createPost(SellerPost sellerId);

}
