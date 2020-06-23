package com.bkdn.studentmanagement.configs.models.structures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {
    private int id;
    private String email;
    private String encrytedPassword;
    private String fullName;
    private String roleCode;
    private String roleName;


}
