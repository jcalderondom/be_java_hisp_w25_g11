package com.example.be_java_hisp_w25_g11.controller;

import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowersDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}")
public class UserController {
    IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(userService.followersSellersCount(1L), HttpStatus.OK);
    }

    @PostMapping("/follow/{userIdToFollow}")
    public ResponseEntity<SuccessDTO> follow(
        @PathVariable Integer userId,
        @PathVariable Integer userIdToFollow
    ) {
        return new ResponseEntity<>(new SuccessDTO(), HttpStatus.OK);
    }

    @GetMapping("/followers/count")
    public ResponseEntity<FollowersCountDTO> followersCount(
        @PathVariable Integer userId
    ) {
        return new ResponseEntity<>(new FollowersCountDTO(), HttpStatus.OK);
    }

    @GetMapping("/followers/list")
    public ResponseEntity<FollowersDTO>  followersList(
        @PathVariable Integer userId,
        @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(new FollowersDTO(), HttpStatus.OK);
    }

    @GetMapping("/followed/list")
    public ResponseEntity<FollowedDTO> followedList(
        @PathVariable Integer userId,
        @RequestParam(required = false) String order
    ) {
        return new ResponseEntity<>(new FollowedDTO(), HttpStatus.OK);
    }

    @PostMapping("/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccessDTO> unfollow(
        @PathVariable Integer userId,
        @PathVariable Integer userIdToUnfollow
    ) {
        return new ResponseEntity<>(new SuccessDTO(), HttpStatus.OK);
    }
}
