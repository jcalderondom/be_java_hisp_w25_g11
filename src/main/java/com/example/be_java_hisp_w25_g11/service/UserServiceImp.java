package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.UserDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.entity.User;
import com.example.be_java_hisp_w25_g11.exception.NotFoundException;
import com.example.be_java_hisp_w25_g11.repository.buyer.BuyerRepositoryImp;
import com.example.be_java_hisp_w25_g11.repository.seller.SellerRepositoryImp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements IUserService{

    private BuyerRepositoryImp buyerRepositoryImp;
    private SellerRepositoryImp sellerRepositoryImp;
    private ModelMapper mapper;

    public UserServiceImp(BuyerRepositoryImp buyerRepositoryImp, SellerRepositoryImp sellerRepositoryImp, ModelMapper mapper) {
        this.buyerRepositoryImp = buyerRepositoryImp;
        this.sellerRepositoryImp = sellerRepositoryImp;
        this.mapper = mapper;
    }

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
        Set<Seller> followedByUser;
        if (sellerRepositoryImp.existing(userId)) {
            Seller sellerResult = sellerRepositoryImp.getAll().stream()
                    .filter(seller -> seller.getId().equals(userId))
                    .findFirst().orElse(null);
            followedByUser = sellerResult != null ? sellerResult.getFollowed() : null;

        } else if (buyerRepositoryImp.existing(userId)) {
            Buyer buyerResult = buyerRepositoryImp.getAll().stream()
                    .filter(seller -> seller.getId().equals(userId))
                    .findFirst().orElse(null);
            followedByUser = buyerResult != null ? buyerResult.getFollowed() : null;

        } else {
            throw new NotFoundException("El usuario ingresado no existe");
        }
        List<UserDTO> followedByUserDto = followedByUser.stream()
                .map(seller -> mapper.map(seller,UserDTO.class))
                .toList();
        return new FollowedDTO(userId,)
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
