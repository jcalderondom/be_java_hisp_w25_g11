package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService{
    @Override
    public SuccessDTO follow(Long userIdToFollow) {
        return null;
    }

    @Override
    public FollowersCountDTO followersSellersCount(Long sellerId) {
        return null;
    }

    @Override
    public FollowersDTO buyersFollowSellers(Long sellerId) {
        return null;
    }

    @Override
    public FollowedDTO sellersFollowingByUsers(Long userId) {
        return null;
    }

    @Override
    public SuccessDTO unfollow(Long userId, Long sellerIdToUnfollow) {
        return null;
    }

    @Override
    public FollowersDTO sortFollowers(String order) {
        return null;
    }

    @Override
    public FollowedDTO sortFollowed(String order) {
        return null;
    }

    @Override
    public boolean isSeller(Long userId) {
        return false;
    }
}
