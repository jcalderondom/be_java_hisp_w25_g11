package com.example.be_java_hisp_w25_g11.repository;

import com.example.be_java_hisp_w25_g11.entity.Buyer;
import com.example.be_java_hisp_w25_g11.exception.ExistingFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BuyerRepositoryImp implements IBuyerRepository{
    private List<Buyer> buyerList = new ArrayList<>();

    @Override
    public List<Buyer> getAllBuyers() {
        return this.buyerList;
    }

    @Override
    public void createBuyer(Buyer buyer) {
        if (existingBuyer(buyer.getId())){
            throw new ExistingFoundException("El comprador que estas intentando crear, ya existe");
        }
        this.buyerList.add(buyer);
    }

    @Override
    public void getBuyer(Long id) {
        getAllBuyers().equals(id);
    }

    @Override
    public void editBuyer(Long id) {

    }

    @Override
    public void deleteBuyer(Long id) {

    }

    @Override
    public boolean existingBuyer(Long id) {
        return buyerList.stream()
                .anyMatch(b -> b.getId().equals(id));
    }
}
