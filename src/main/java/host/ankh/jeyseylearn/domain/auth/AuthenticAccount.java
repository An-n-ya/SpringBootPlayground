package host.ankh.jeyseylearn.domain.auth;

import host.ankh.jeyseylearn.domain.account.Account;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@Slf4j
public class AuthenticAccount extends Account implements UserDetails {

    //  放置实例权限的哈希set
    private Collection<GrantedAuthority> authorities = new HashSet<>();

    public AuthenticAccount() {
        super();
        authorities.add(new SimpleGrantedAuthority(Role.USER));
    }

    @Override
    public String getPassword() {
        String pass = super.getPassword();
        return pass;
    }

    public AuthenticAccount(Account account) {
        // 调用无参构造器
        this();
        BeanUtils.copyProperties(account, this);
        // FIXME: id 不知道为什么没有复制过去，id的值为null
        this.id = account.getId();
        log.debug(this.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * 账户是否过期，默认不过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否被锁定, 默认锁定
     * @return true
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否过期，默认不过期
     * @return true
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    /**
     * 是否可用，默认可用
     * @return true
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    @Override
    public String toString() {
        return "AuthenticAccount{" +
                "id =" + getId() +
                ", username=" + getUsername() +
                ", password=" + getPassword() +
                ", name=" + getName() +
                ", email=" + getEmail() +
                ", telephone=" + getTelephone() +
                ", authorities=" + authorities +
                '}';
    }
}
