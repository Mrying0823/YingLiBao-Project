package org.example.ylb.front.pojo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author 邓和颖
 * @since 2023/10/12 10:32
 */
public class CustomerUserDetails implements UserDetails {

    // 用户手机号码
    private final String phone;

    // 账号密码
    private final String password;

    // 用户是否启用
    private final boolean enabled;

    // 用户账户是否未过期
    private final boolean accountNonExpired;

    // 用户账户是否未锁定
    private final boolean accountNonLocked;

    // 用户凭证是否未过期
    private final boolean credentialsNonExpired;

    public CustomerUserDetails(String phone,
                               String password,
                               boolean enabled,
                               boolean accountNonExpired,
                               boolean accountNonLocked,
                               boolean credentialsNonExpired) {
        this.phone = phone;
        this.password = password;
        this.enabled = enabled;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
