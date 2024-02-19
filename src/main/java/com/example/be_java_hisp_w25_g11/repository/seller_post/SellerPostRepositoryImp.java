package com.example.be_java_hisp_w25_g11.repository.seller_post;

import com.example.be_java_hisp_w25_g11.entity.Seller;
import com.example.be_java_hisp_w25_g11.entity.SellerPost;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class SellerPostRepositoryImp implements ISellerPostRepository {
    private final Map<Long,SellerPost> sellerPosts;
    private final Map<Long,SellerPost> products;

    public SellerPostRepositoryImp() {
        this.sellerPosts = new HashMap<>();
        this.products = new HashMap<>();
    }

    @Override
    public List<SellerPost> getAll() {
        return sellerPosts
                .values()
                .stream().toList();
    }

    @Override
    public boolean create(SellerPost sellerPost) {
        if (existing(sellerPost.getId())) {
            return false;
        }
        this.sellerPosts.put(sellerPost.getId(), sellerPost);
        return true;

    }

    @Override
    public Optional<SellerPost> get(Long id) {
        return Optional.ofNullable(sellerPosts.get(id));

    }

    @Override
    public boolean edit(Long id, SellerPost sellerPost) {
        if (get(id).isEmpty()) {
            return false;
        }

        sellerPosts.put(id, sellerPost);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return sellerPosts.remove(id) != null;
    }

    @Override
    public boolean existing(Long id) {
        return sellerPosts.containsKey(id);
    }
}
