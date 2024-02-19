package com.example.be_java_hisp_w25_g11.repository.seller;

import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class SellerRepositoryImp implements ISellerRepository {
    private final Map<Long, Seller> sellers;

    public SellerRepositoryImp() {
        this.sellers = new HashMap<>();
    }

    @Override
    public List<Seller> getAll() {
        return sellers
                .values()
                .stream().toList();
    }

    @Override
    public boolean create(Seller user) {
        if (existing(user.getId())){
            return false;
        }

        this.sellers.put(user.getId(), user);
        return true;
    }

    @Override
    public Optional<Seller> get(Long id) {
        return Optional.ofNullable(sellers.get(id));
    }

    @Override
    public boolean edit(Long id, Seller seller) {
        if (get(id).isEmpty()) {
            return false;
        }

        sellers.put(id,seller);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return sellers.remove(id) != null;
    }

    @Override
    public boolean existing(Long id) {
        return sellers.containsKey(id);
    }
}
