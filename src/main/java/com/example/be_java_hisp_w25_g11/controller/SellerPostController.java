package com.example.be_java_hisp_w25_g11.controller;

import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.dto.request.CreatePostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class SellerPostController {
    @PostMapping("/post")
    public ResponseEntity<SuccessDTO> postNewProduct(
            @RequestBody CreatePostDTO request
    ) {
        // TODO: Full implementation (status codes: 200 ok & bad request, bodyless or dto)
        return new ResponseEntity<>(new SuccessDTO(), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<?> getFollowedPostsList(
            @PathVariable Integer userId,
            @RequestParam(required = false) String order
    ) {
        // TODO: Full implementation (response: user_id, posts made (in last 2 weeks) by followed sellers list)
        return null;
    }
}