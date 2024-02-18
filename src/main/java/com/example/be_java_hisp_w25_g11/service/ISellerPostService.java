package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.SellerPostDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.SellerPost;

public interface ISellerPostService {
    SuccessDTO createPost(SellerPostDTO sellerPost);
}
