package com.example.be_java_hisp_w25_g11.service;

import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.repository.buyer.IBuyerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller.ISellerRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService {
    private IBuyerRepository buyerRepository;
    private ISellerRepository sellerRepository;

    public UserServiceImp(
        IBuyerRepository buyerRepository,
        ISellerRepository sellerRepository
    ) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    private Object getUser(Long id) {
        if (buyerRepository.existing(id)) {
            return buyerRepository.get(id).get();
        } else if (sellerRepository.existing(id)) {
            return sellerRepository.get(id).get();
        } else {
            throw new NotFoundException("El usuario con id="+id+" no existe."); // TODO NotFoundException
        }
    }

    @Override
    public SuccessDTO follow(Long userId, Long userIdToFollow) {
        Object user = getUser(userId);
        Object userToFollow = getUser(userIdToFollow);

        if (!(userToFollow instanceof Seller)) {
            throw new BadRequestException("El comprador a seguir debe ser un vendedor."); // TODO BadRequestException
        }

        if (user instanceof Buyer) {
            if (((Buyer) user).getFollowed().contains(userIdToFollow)) {
                throw new BadRequestException("El comprador con id="+userId+" ya sigue al vendedor con id"+userToFollow+"."); // TODO BadRequestException
            }
            ((Buyer) user).getFollowed().add(userIdToFollow);
            ((Seller) userToFollow).getFollowers().add(userId);
        } else if (user instanceof Seller) {
            if (((Seller) user).getFollowed().contains(userIdToFollow)) {
                throw new BadRequestException("El vendedor con id="+userId+" ya sigue al vendedor con id"+userToFollow+"."); // TODO BadRequestException
            }
            ((Seller) user).getFollowed().add(userIdToFollow);
            ((Seller) userToFollow).getFollowers().add(userId);
        } else {
            throw new BadRequestException("El usuario con id="+userId+" no es ni comprador ni vendedor."); // TODO BadRequestException
        }
        return new SuccessDTO("El usuario con id="+userId+" ahora sigue al vendedor con id="+userIdToFollow+".");
    }

    @Override
    public FollowersCountDTO followersSellersCount(Long sellerId) {
        return new FollowersCountDTO (
                1L,
                "test",
                buyerRepository.getAll().size()
        );
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
        Object user = getUser(userId);
        Object userToUnfollow = getUser(sellerIdToUnfollow);

        if (user instanceof Buyer) {
            if (!((Buyer) user).getFollowed().contains(sellerIdToUnfollow)) {
                throw new BadRequestException("El comprador con id="+userId+" no sigue al vendedor con id"+userToUnfollow+"."); // TODO BadRequestException
            }
            ((Buyer) user).getFollowed().remove(sellerIdToUnfollow);
            ((Seller) userToUnfollow).getFollowers().remove(userId);
        } else if (user instanceof Seller) {
            if (!((Seller) user).getFollowed().contains(sellerIdToUnfollow)) {
                throw new BadRequestException("El vendedor con id="+userId+" no sigue al vendedor con id"+userToUnfollow+"."); // TODO BadRequestException
            }
            ((Seller) user).getFollowed().remove(sellerIdToUnfollow);
            ((Seller) userToUnfollow).getFollowers().remove(userId);
        } else {
            throw new BadRequestException("El usuario con id="+userId+" no es ni comprador ni vendedor."); // TODO BadRequestException
        }
        return new SuccessDTO("El usuario con id="+userId+" ha dejado de seguir al vendedor con id="+sellerIdToUnfollow+".");
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
