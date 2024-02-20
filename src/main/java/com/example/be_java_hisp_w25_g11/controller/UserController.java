package com.example.be_java_hisp_w25_g11.controller;

import com.example.be_java_hisp_w25_g11.dto.response.FollowedDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerCountDTO;
import com.example.be_java_hisp_w25_g11.dto.response.FollowerDTO;
import com.example.be_java_hisp_w25_g11.dto.SuccessDTO;
import com.example.be_java_hisp_w25_g11.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/{userId}")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @PostMapping("/follow/{userIdToFollow}")
    public ResponseEntity<SuccessDTO> follow(
        @PathVariable Integer userId,
        @PathVariable Integer userIdToFollow
    ) {
        return new ResponseEntity<>(new SuccessDTO(), HttpStatus.OK);
    }

    @GetMapping("/followers/count")
    public ResponseEntity<FollowerCountDTO> followersCount(
        @PathVariable Long userId
    ) {
        return new ResponseEntity<>(userService.followersSellersCount(userId), HttpStatus.OK);
    }

    @GetMapping("/followers/list")
    public ResponseEntity<FollowerDTO>  followersList(
        @PathVariable Long userId

    ) {
        return new ResponseEntity<>(userService.buyersFollowSellers(userId), HttpStatus.OK);
    }

    @GetMapping("/followed/list")
    public ResponseEntity<FollowedDTO> followedList(
        @PathVariable Long userId,
        @RequestParam(required = false) String order
    ) {return ResponseEntity.ok(this.userService.sortFollowed(userId,order));}

    @PostMapping("/unfollow/{userIdToUnfollow}")
    public ResponseEntity<SuccessDTO> unfollow(
        @PathVariable Integer userId,
        @PathVariable Integer userIdToUnfollow
    ) {
        return new ResponseEntity<>(new SuccessDTO(), HttpStatus.OK);
    }
}
