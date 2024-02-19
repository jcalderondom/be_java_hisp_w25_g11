package com.example.be_java_hisp_w25_g11.dto;

import com.example.be_java_hisp_w25_g11.dto.request.SellerPostDTO;
import com.example.be_java_hisp_w25_g11.entity.SellerPost;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostSellersFollowingByUserDto {


    @JsonProperty("user_id")
    private Long user_id;
    private List<SellerPostDTO> posts;

}
