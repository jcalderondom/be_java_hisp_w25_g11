package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.UserDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.exception.NotFoundException;
import com.example.be_java_hisp_w25_g11.repository.buyer.BuyerRepositoryImp;
import com.example.be_java_hisp_w25_g11.repository.seller.SellerRepositoryImp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements IUserService{
    private BuyerRepositoryImp buyerRepositoryImp;
    private SellerRepositoryImp sellerRepositoryImp;
    private ModelMapper mapper;

    public UserServiceImp(
            BuyerRepositoryImp buyerRepositoryImp,
            SellerRepositoryImp sellerRepositoryImp,
            ModelMapper mapper
    ) {
        this.buyerRepositoryImp = buyerRepositoryImp;
        this.sellerRepositoryImp = sellerRepositoryImp;
        this.mapper = mapper;
    }

    @Override
    public SuccessDTO follow(Long userIdToFollow) {
        return null;
    }

    @Override
    public FollowerCountDTO followersSellersCount(Long sellerId) {
        return null;
    }

    @Override
    public FollowerDTO buyersFollowSellers(Long sellerId) {
        Optional<Seller> seller = sellerRepositoryImp.get(sellerId);
        if(seller.isEmpty()){
            throw new NotFoundException("Buyer does not exists");
        }

        List<UserDTO> followers = seller.get().getFollowers()
                .stream()
                .map(u -> mapper.map(u, UserDTO.class))
                .toList();

        return new FollowerDTO(sellerId,seller.get().getName(),followers);
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
    public FollowerDTO sortFollowers(String order) {
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
