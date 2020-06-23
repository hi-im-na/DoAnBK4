package com.bkdn.studentmanagement.configs.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.bkdn.studentmanagement.configs.models.structures.AccountInfo;

public class AccountModel extends User {
    private static final long serialVersionUID = 1L;
    private final AccountInfo accountInfo;
    
    public AccountModel(AccountInfo accountInfo, Collection<? extends GrantedAuthority> authorities) {
        super(accountInfo.getEmail(), accountInfo.getEncrytedPassword(), authorities);
        this.accountInfo = accountInfo;
    }

    public AccountModel(AccountInfo accountInfo, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(accountInfo.getEmail(), accountInfo.getEncrytedPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.accountInfo = accountInfo;
    }

    public AccountInfo getUserInfo() {
        return this.accountInfo;
    }

}
