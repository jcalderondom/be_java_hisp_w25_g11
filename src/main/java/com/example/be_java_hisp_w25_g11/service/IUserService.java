package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;

public interface IUserService {
    SuccessDTO follow(Long userIdToFollow);
    FollowersCountDTO followersSellersCount(Long sellerId);
    FollowersDTO buyersFollowSellers(Long sellerId);
    FollowedDTO sellersFollowingByUsers(Long userId);
    SuccessDTO unfollow(Long userId, Long sellerIdToUnfollow);
    FollowersDTO sortFollowers(String order);
    FollowedDTO sortFollowed(String order);
    boolean isSeller(Long userId);
}
