package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.request.SellerPostDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;

public interface ISellerPostService {
    SuccessDTO createPost(SellerPostDTO sellerPost);
}
