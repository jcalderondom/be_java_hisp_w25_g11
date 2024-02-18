package com.example.be_java_hisp_w25_g11.repository;

import com.example.be_java_hisp_w25_g11.entity.Buyer;
import java.util.List;

public interface IBuyerRepository {
    List<Buyer> getAllBuyers();
    void createBuyer(Buyer buyer);
    void getBuyer(Long id);
    void editBuyer(Long id);
    void deleteBuyer(Long id);
    boolean existingBuyer(Long id);

}
