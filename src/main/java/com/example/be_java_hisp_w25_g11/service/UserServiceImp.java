package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.UserDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.exception.NotFoundException;
import com.example.be_java_hisp_w25_g11.repository.buyer.BuyerRepositoryImp;
import com.example.be_java_hisp_w25_g11.repository.seller.SellerRepositoryImp;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Set<Long> followedBySeller;
        Set<Seller> followedByBuyer;
        List<UserDTO> userDTOList = null;
        String name;
        if (sellerRepositoryImp.existing(userId)) {
            name = sellerRepositoryImp.get(userId).get().getName();
            Seller sellerResult = sellerRepositoryImp.getAll().stream()
                    .filter(seller -> seller.getId().equals(userId))
                    .findFirst().orElse(null);
            followedBySeller = sellerResult != null ? sellerResult.getFollowed() : null;
            List<Seller> sellerList = new ArrayList<>();
            if (followedBySeller != null) {
                for (Long sellerFind : followedBySeller) {
                    sellerList.addAll(sellerRepositoryImp.getAll().stream()
                            .filter(seller -> seller.getId().equals(sellerFind))
                            .toList());
                    System.out.println("followedseller" + followedBySeller);
                    userDTOList = sellerList.stream()
                            .map(seller -> mapper.map(seller, UserDTO.class))
                            .collect(Collectors.toList());
                }
            }
        } else if (buyerRepositoryImp.existing(userId)) {
            name = buyerRepositoryImp.get(userId).get().getName();
            Buyer buyerResult = buyerRepositoryImp.getAll().stream()
                    .filter(seller -> seller.getId().equals(userId))
                    .findFirst().orElse(null);
            followedByBuyer = buyerResult != null ? buyerResult.getFollowed() : null;
            userDTOList = followedByBuyer.stream()
                    .map(buyer -> mapper.map(buyer,UserDTO.class))
                    .toList();
        } else {
            throw new NotFoundException("The current id doesn't exist");
        }
        return new FollowedDTO(userId, name, userDTOList);
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
