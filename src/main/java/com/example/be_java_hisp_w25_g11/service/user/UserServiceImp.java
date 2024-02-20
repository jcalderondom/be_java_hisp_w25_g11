package com.example.be_java_hisp_w25_g11.service.user;

import com.example.be_java_hisp_w25_g11.dto.UserDTO;
import com.example.be_java_hisp_w25_g11.dto.commons.enums.EnumNameOrganizer;
import com.example.be_java_hisp_w25_g11.dto.request.OrganizerByNameDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerDTO;
import com.example.be_java_hisp_w25_g11.dto.response.SuccessDTO;
import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.exception.BadRequestException;
import com.example.be_java_hisp_w25_g11.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import com.example.be_java_hisp_w25_g11.repository.buyer.IBuyerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller.ISellerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Stream;


@Service
public class UserServiceImp implements IUserService {
    private final IBuyerRepository buyerRepository;
    private final ISellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    public UserServiceImp(
        IBuyerRepository buyerRepository,
        ISellerRepository sellerRepository,
        ModelMapper modelMapper
    ) {
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    private Object getUser(Integer id) {
        if (buyerRepository.existing(id)) {
            return buyerRepository.get(id).get();
        } else if (sellerRepository.existing(id)) {
            return sellerRepository.get(id).get();
        } else {
            throw new NotFoundException("El usuario con id="+id+" no existe.");
        }
    }

    @Override
    public SuccessDTO follow(Integer userId, Integer userIdToFollow) {
        Object user = getUser(userId);
        Object userToFollow = getUser(userIdToFollow);

        if (!(userToFollow instanceof Seller)) {
            throw new BadRequestException("El comprador a seguir debe ser un vendedor.");
        }

        if (user instanceof Buyer) {
            if (((Buyer) user).getFollowed().contains(userIdToFollow)) {
                throw new BadRequestException("El comprador con id="+userId+" ya sigue al vendedor con id"+userToFollow+".");
            }
            ((Buyer) user).getFollowed().add(userIdToFollow);
            ((Seller) userToFollow).getFollowers().add(userId);
        } else if (user instanceof Seller) {
            if (((Seller) user).getFollowed().contains(userIdToFollow)) {
                throw new BadRequestException("El vendedor con id="+userId+" ya sigue al vendedor con id"+userToFollow+".");
            }
            ((Seller) user).getFollowed().add(userIdToFollow);
            ((Seller) userToFollow).getFollowers().add(userId);
        } else {
            throw new BadRequestException("El usuario con id="+userId+" no es ni comprador ni vendedor.");
        }
        return new SuccessDTO("El usuario con id="+userId+" ahora sigue al vendedor con id="+userIdToFollow+".");
    }

    @Override
    public FollowerCountDTO followersSellersCount(Integer sellerId) {
        Optional<Seller> seller = sellerRepository.get(sellerId);
        if(seller.isEmpty())
            throw new NotFoundException("Buyer does not exists");
        int followersCount = seller.get().getFollowers().size();
        return new FollowerCountDTO (sellerId, seller.get().getName(), followersCount);


    }

    @Override
    public FollowerDTO buyersFollowSellers(Integer sellerId) {
        Optional<Seller> seller = sellerRepository.get(sellerId);
        if(seller.isEmpty()){
            if(buyerRepository.get(sellerId).isPresent()){
                throw new BadRequestException("Buyer does not have followers");
            }
            throw new NotFoundException("Buyer does not exists");
        }

        List<UserDTO> followers = seller.get().getFollowers()
                .stream()
                .map(followerId ->{
                    if (buyerRepository.existing(followerId)
                    ) {
                        return buyerRepository.get(followerId);

                    }
                    else{
                        return sellerRepository.get(followerId);
                    }
                })
                .map(u -> {return modelMapper.map(u.get(), UserDTO.class);})
                .toList();
        return new FollowerDTO(sellerId,seller.get().getName(),followers);
    }

    @Override
    public FollowedDTO sellersFollowingByUsers(Integer userId) {
        return null;
    }

    @Override
    public SuccessDTO unfollow(Integer userId, Integer sellerIdToUnfollow) {
        Object user = getUser(userId);
        Object userToUnfollow = getUser(sellerIdToUnfollow);

        if (user instanceof Buyer) {
            if (!((Buyer) user).getFollowed().contains(sellerIdToUnfollow)) {
                throw new BadRequestException("El comprador con id="+userId+" no sigue al vendedor con id"+userToUnfollow+".");
            }
            ((Buyer) user).getFollowed().remove(sellerIdToUnfollow);
            ((Seller) userToUnfollow).getFollowers().remove(userId);
        } else if (user instanceof Seller) {
            if (!((Seller) user).getFollowed().contains(sellerIdToUnfollow)) {
                throw new BadRequestException("El vendedor con id="+userId+" no sigue al vendedor con id"+userToUnfollow+".");
            }
            ((Seller) user).getFollowed().remove(sellerIdToUnfollow);
            ((Seller) userToUnfollow).getFollowers().remove(userId);
        } else {
            throw new BadRequestException("El usuario con id="+userId+" no es ni comprador ni vendedor.");
        }
        return new SuccessDTO("El usuario con id="+userId+" ha dejado de seguir al vendedor con id="+sellerIdToUnfollow+".");
    }

    @Override
    public FollowerDTO sortFollowers(OrganizerByNameDTO organizer) {
        if(this.sellerRepository.get(organizer.getUserId()).isEmpty()){
            throw new NotFoundException(String.format("User with Id "+organizer.getUserId()+" Not found"));
        }
        Seller seller = this.sellerRepository.get(organizer.getUserId()).get();
        return new FollowerDTO(organizer.getUserId(),seller.getName(),this.getSortedUsers(seller.getFollowers(),organizer.getOrder()));
    }

    @Override
    public FollowedDTO sortFollowed(OrganizerByNameDTO organizer) {
        if(this.sellerRepository.get(organizer.getUserId()).isEmpty()){
            throw new NotFoundException(String.format("User with Id "+organizer.getUserId()+" Not found"));
        }
        Seller seller = this.sellerRepository.get(organizer.getUserId()).get();
        return new FollowedDTO(organizer.getUserId(),seller.getName(),this.getSortedUsers(seller.getFollowed(),organizer.getOrder()));
    }

    private List<UserDTO> getSortedUsers(Set<Integer> usersId, EnumNameOrganizer organizer){
        return usersId.stream()
                .filter(this::isThisIdInSellerOrBuyerRepositories)
                .map(this::mapBuyersAndSeyersToUserDTO)
                .sorted(Comparator.comparing(UserDTO::getName,organizer.getComparator())).toList();
    }

    private UserDTO mapBuyersAndSeyersToUserDTO(Integer sellerId){
        if(this.sellerRepository.get(sellerId).isPresent()){
            Seller lambdaSeller = this.sellerRepository.get(sellerId).get();
            return new UserDTO(lambdaSeller.getId(),lambdaSeller.getName());
        }
        Buyer lambdaBuyer = this.buyerRepository.get(sellerId).get();
        return new UserDTO(lambdaBuyer.getId(),lambdaBuyer.getName());
    }

    //Filtramos para asegurarnos de que él id del usuario a mostrar sea un usuario existente como cliente o como vendedor
    private boolean isThisIdInSellerOrBuyerRepositories(Integer userId){
        return  this.buyerRepository.get(userId).isPresent() || this.sellerRepository.get(userId).isPresent();
    }

    @Override
    public boolean isSeller(Integer userId) {
        return false;
    }
}
