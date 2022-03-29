package com.phonebook.client;

import com.phonebook.dto.UserDto;
import com.phonebook.dto.UserReq;
import com.phonebook.dto.UserResp;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "phonebook-api", url = "${phonebook-api}")
public interface PhonebookClient {

    @GetMapping(value = "/status")
    Object getStatus();

    @GetMapping(value = "/user/list")
    List<UserDto> getAllUsers();

    @PostMapping(value = "/user/add")
    UserResp addUser(@RequestBody UserReq request);

    @PutMapping(value = "/user/edit/{userId}")
    UserResp editUser(@PathVariable String userId, @RequestBody UserReq request);

    @DeleteMapping(value = "/user/delete/{userId}")
    UserResp deleteUser(@PathVariable String userId);
}
