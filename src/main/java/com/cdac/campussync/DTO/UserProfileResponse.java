package com.cdac.campussync.DTO;

import com.cdac.campussync.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserProfileResponse {
    private Long id;
    private String username;
    private Role role;
}
