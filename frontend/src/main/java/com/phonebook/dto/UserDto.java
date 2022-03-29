package com.phonebook.dto;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private UUID userId;
    private String name;
    private String phone;
}
