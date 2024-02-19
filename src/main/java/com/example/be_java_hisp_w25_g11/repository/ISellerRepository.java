package com.example.be_java_hisp_w25_g11.repository;

import com.example.be_java_hisp_w25_g11.entity.Seller;

import java.util.List;

public interface ISellerRepository {
    List<Seller> getAllSellers();
    void createSeller(Long id, String name);
    void getSellerById(Long id);
    void editSeller(Long id);
    void deleteSeller(Long id);
}
