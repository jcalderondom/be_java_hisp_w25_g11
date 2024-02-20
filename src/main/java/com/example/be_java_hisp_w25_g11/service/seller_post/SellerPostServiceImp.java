package com.example.be_java_hisp_w25_g11.service.seller_post;

import com.example.be_java_hisp_w25_g11.dto.SellerPostDTO;
import com.example.be_java_hisp_w25_g11.dto.request.CreatePostRequestDTO;
import com.example.be_java_hisp_w25_g11.dto.response.SellerPostsListDTO;
import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Product;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.entity.SellerPost;
import com.example.be_java_hisp_w25_g11.exception.NotFoundException;
import com.example.be_java_hisp_w25_g11.repository.buyer.IBuyerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller.ISellerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller_post.ISellerPostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SellerPostServiceImp implements ISellerPostService {
    private final ISellerPostRepository sellerPostRepository;
    private final IBuyerRepository buyerRepository;
    private final ISellerRepository sellerRepository;
    private final ModelMapper modelMapper;

    public SellerPostServiceImp (
            ISellerPostRepository sellerPostRepository,
            IBuyerRepository buyerRepository,
            ISellerRepository sellerRepository,
            ModelMapper modelMapper
    ) {
        this.sellerPostRepository = sellerPostRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SellerPostDTO createPost(CreatePostRequestDTO request) {
        Optional<Seller> seller = sellerRepository.get(request.getUserId());
        if (seller.isEmpty())
            throw new NotFoundException("No existe un vendedor con ese ID");

        SellerPost sellerPost = new SellerPost (
                request.getUserId(),
                null,
                request.getDate(),
                modelMapper.map(request.getProduct(), Product.class),
                request.getCategory(),
                request.getPrice(),
                seller.get()
        );

        sellerPost = sellerPostRepository.create(sellerPost);
        seller.get().getPosts().add(sellerPost);

        return modelMapper.map(sellerPost, SellerPostDTO.class);
    }

    @Override
    public SellerPostsListDTO getFollowedSellersLatestPosts(Integer userId) {
        List<SellerPost> posts = new ArrayList<>();

        Optional<Buyer> buyer = buyerRepository.get(userId);
        if (buyer.isPresent())
            posts = getMergedPostsList(buyer.get().getFollowed());


        Optional<Seller> seller = sellerRepository.get(userId);
        if (seller.isPresent())
            posts = getMergedPostsList(seller.get().getFollowed());

        return new SellerPostsListDTO(
                userId,
                posts
                        .stream()
                        .map(v -> modelMapper.map(v, SellerPostDTO.class))
                        .toList()
        );
    }

    private List<SellerPost> getMergedPostsList(Set<Integer> followed) {
        List<SellerPost> posts;
        posts = followed
                .stream()
                .map(s -> {
                    Optional<Seller> followedSeller = sellerRepository.get(s);
                    if (followedSeller.isEmpty())
                        throw new NotFoundException("No se pudo encontrar la información de un vendedor");

                    return followedSeller
                            .get()
                            .getPosts()
                            .stream()
                            .filter(p -> p.getDate().isAfter(LocalDate.now().minusWeeks(2)))
                            .toList();
                })
                .flatMap(List::stream)
                .toList();

        return posts;
    }
}
