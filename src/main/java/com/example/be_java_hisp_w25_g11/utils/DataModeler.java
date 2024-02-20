package com.example.be_java_hisp_w25_g11.utils;

import com.example.be_java_hisp_w25_g11.entity.*;
import com.example.be_java_hisp_w25_g11.repository.buyer.IBuyerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller.ISellerRepository;
import com.example.be_java_hisp_w25_g11.repository.seller_post.ISellerPostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class DataModeler {

    IBuyerRepository userRepository;
    ISellerRepository sellerRepository;
    ISellerPostRepository sellerPostRepository;

    public DataModeler(
            IBuyerRepository userRepository,
            ISellerRepository sellerRepository,
            ISellerPostRepository sellerPostRepository
    ) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.sellerPostRepository = sellerPostRepository;
    }

    @PostConstruct
    public void load() {

        List<Buyer> users = new ArrayList<>(List.of(
                new Buyer(1L, "Jhon Perez"),
                new Buyer(2L, "Carlos Martinez"),
                new Buyer(3L, "Maria Rodriguez"),
                new Buyer(4L, "Pedro Lopez"),
                new Buyer(5L, "Eduardo Gomez")
        ));

        List<Seller> sellers = new ArrayList<>(List.of(
                new Seller(6L, "Natalia Sanchez"),
                new Seller(7L, "Samuel Diaz"),
                new Seller(8L, "Jorge Hernandez"),
                new Seller(9L, "Oscar Ramirez"),
                new Seller(10L, "Diego Gonzalez"),
                new Seller(11L, "Juan Torres"),
                new Seller(12L, "Andres Flores")
        ));

        List<Product> products = new ArrayList<>(List.of(
                new Product(1L, "Monitor", "Oficina", "Samsumg", "Black", "1080p"),
                new Product(2L, "Silla Gamer", "Gamer", "Razer", "Black", "Special Edition"),
                new Product(3L, "Mouse", "Personal", "Apple", "White", "Only for Mac"),
                new Product(4L, "Teclado", "Gamer", "Logitech", "White", "With RGB"),
                new Product(5L, "Audífonos", "Studio", "Beats", "Red", "Wireless")
        ));

        List<SellerPost> posts = new ArrayList<>(List.of(
                new SellerPost(1L, LocalDate.now(), 1, 10.0, false, 0.0, sellers.get(0), products.get(0)),
                new SellerPost(2L, LocalDate.now(), 1, 10.0, false, 0.0, sellers.get(0), products.get(0)),
                new SellerPost(3L, LocalDate.now(), 1, 10.0, false, 0.0, sellers.get(0), products.get(0)),
                new SellerPost(4L, LocalDate.now(), 1, 10.0, false, 0.0, sellers.get(0), products.get(0)),
                new SellerPost(5L, LocalDate.now(), 1, 10.0, false, 0.0, sellers.get(0), products.get(0))
        ));

        // Assign posts to sellers
        sellers.get(0).getPosts().add(posts.get(0));
        sellers.get(0).getPosts().add(posts.get(1));
        sellers.get(1).getPosts().add(posts.get(2));
        sellers.get(2).getPosts().add(posts.get(3));
        sellers.get(3).getPosts().add(posts.get(4));

        // Set followed
        users.get(0).setFollowed(new HashSet<>(List.of(
                sellers.get(0),
                sellers.get(1),
                sellers.get(2)
        )));

        users.get(1).setFollowed(new HashSet<>(List.of(
                sellers.get(0),
                sellers.get(2)
        )));

        users.get(2).setFollowed(new HashSet<>(List.of(
                sellers.get(1),
                sellers.get(2),
                sellers.get(3)
        )));

        users.get(4).setFollowed(new HashSet<>(List.of(
                sellers.get(3)
        )));

        sellers.get(3).setFollowed(new HashSet<>(List.of(
                sellers.get(1).getId(),
                sellers.get(2).getId()
        )));

        // Set followers

        sellers.get(0).setFollowers(new HashSet<>(List.of(
                users.get(0).getId(),
                users.get(1).getId()
        )));

        sellers.get(1).setFollowers(new HashSet<>(List.of(
                users.get(0).getId(),
                users.get(2).getId(),
                sellers.get(3).getId()
        )));

        sellers.get(2).setFollowers(new HashSet<>(List.of(
                users.get(0).getId(),
                users.get(1).getId(),
                users.get(2).getId(),
                sellers.get(3).getId()
        )));

        sellers.get(3).setFollowers(new HashSet<>(List.of(
                users.get(2).getId(),
                users.get(4).getId()
        )));

        // Add everything to repositories
        userRepository.createAll(users);
        sellerRepository.createAll(sellers);
        sellerPostRepository.createAll(posts);
    }
}