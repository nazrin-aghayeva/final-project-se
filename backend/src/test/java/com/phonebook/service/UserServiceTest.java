package com.phonebook.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.BDDMockito.willThrow;

import com.phonebook.entity.UserEntity;
import com.phonebook.model.UserReq;
import com.phonebook.model.UserResp;
import com.phonebook.repo.UserRepo;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        given(userRepo.save(any())).willReturn(UserEntity.builder().build());
        UserResp saved = userService.save(UserReq.builder().build());
        then(userRepo).should().save(any());
        assertThat(saved).isNotNull();
    }

    @Test
    void saveShouldFail() {
        given(userRepo.save(any())).willThrow(new RuntimeException());
        UserResp saved = userService.save(UserReq.builder().build());
        then(userRepo).shouldHaveNoMoreInteractions();
        assertThat(saved).isNotNull();
    }

    @Test
    void edit() {
        UserEntity entity = UserEntity.builder().build();
        given(userRepo.findById(anyString())).willReturn(Optional.of(entity));
        given(userRepo.save(any())).willReturn(entity);
        UserResp resp = userService.edit("id", UserReq.builder().build());
        then(userRepo).should().findById(anyString());
        then(userRepo).should().save(any());
        assertThat(resp).isNotNull();
    }

    @Test
    void editShouldFail() {
        given(userRepo.findById(anyString())).willReturn(Optional.empty());
        UserResp resp = userService.edit("id", UserReq.builder().build());
        then(userRepo).shouldHaveNoMoreInteractions();
        assertThat(resp).isNotNull();
    }

    @Test
    void delete() {
        willDoNothing().given(userRepo).deleteById(anyString());
        UserResp resp = userService.delete("id");
        then(userRepo).should().deleteById(anyString());
        assertThat(resp).isNotNull();
    }

    @Test
    void deleteShouldFail() {
        willThrow(new RuntimeException()).given(userRepo).deleteById(anyString());
        UserResp resp = userService.delete("id");
        then(userRepo).shouldHaveNoMoreInteractions();
        assertThat(resp).isNotNull();
    }

    @Test
    void getAllUsers() {
        given(userRepo.findAll()).willReturn(List.of(UserEntity.builder().build()));
        List<UserEntity> allUsers = userService.getAllUsers();
        then(userRepo).should().findAll();
        assertThat(allUsers).hasSize(1);
    }
}