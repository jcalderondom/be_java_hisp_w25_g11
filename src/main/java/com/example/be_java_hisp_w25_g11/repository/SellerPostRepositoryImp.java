package com.example.be_java_hisp_w25_g11.repository;

import com.example.be_java_hisp_w25_g11.entity.SellerPost;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SellerPostRepositoryImp implements ISellerPostRepository{
    private List<SellerPost> sellerPosts = new ArrayList<>();
    private List<SellerPost> products = new ArrayList<>();

    @Override
    public List<SellerPost> getAllSellerPosts() {
        return null;
    }

    @Override
    public void createPost(SellerPost sellerId) {

    }
}
