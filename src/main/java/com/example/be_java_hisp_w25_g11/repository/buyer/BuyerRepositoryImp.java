package com.example.be_java_hisp_w25_g11.repository.buyer;

import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.exception.ExistingFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BuyerRepositoryImp implements IBuyerRepository {
    private final Map<Long, Buyer> buyers;

    public BuyerRepositoryImp() {
        this.buyers = new HashMap<>();
    }

    @Override
    public List<Buyer> getAll() {
        return buyers
                .values()
                .stream().toList();
    }

    @Override
    public List<Buyer> createAll(List<Buyer> entities) {
        entities.forEach(b -> buyers.put(b.getId(), b));
        return entities;    }

    @Override
    public boolean create(Buyer user) {
        if (existing(user.getId())){
            return false;
        }

        this.buyers.put(user.getId(), user);
        return true;
    }

    @Override
    public Optional<Buyer> get(Long id) {
        return Optional.ofNullable(buyers.get(id));
    }

    @Override
    public boolean edit(Long id, Buyer buyer) {
        if (get(id).isEmpty()) {
            return false;
        }

        buyers.put(id,buyer);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        return buyers.remove(id) != null;
    }

    @Override
    public boolean existing(Long id) {
        return buyers.containsKey(id);
    }
}
