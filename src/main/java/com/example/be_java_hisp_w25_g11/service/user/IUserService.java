package com.example.be_java_hisp_w25_g11.service.user;

import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerDTO;
import com.example.be_java_hisp_w25_g11.dto.response.SuccessDTO;

public interface IUserService {

    SuccessDTO follow(Integer userId, Integer userIdToFollow);
    FollowerCountDTO followersSellersCount(Integer sellerId);
    FollowerDTO buyersFollowSellers(Integer sellerId);
    FollowedDTO sellersFollowingByUsers(Integer userId);
    SuccessDTO unfollow(Integer userId, Integer sellerIdToUnfollow);
    FollowerDTO sortFollowers(Integer id, String order);
    FollowedDTO sortFollowed(Integer id,String order);
    boolean isSeller(Integer userId);
}