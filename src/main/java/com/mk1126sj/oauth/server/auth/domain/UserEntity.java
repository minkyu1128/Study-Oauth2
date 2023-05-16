package com.mk1126sj.oauth.server.auth.domain;


import com.mk1126sj.oauth.server.core.code.StateCd;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "oa_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_userId", columnNames = {"user_id"})
        })
@Schema(name = "UserEntity", description = "사용자")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id", length = 50)
    @Schema(required = true, title = "사용자ID", example = " ", description = " ")
    private String userId;

    @Column(name = "password", nullable = false, length = 100)
    @Schema(required = true, title = "비밀번호", example = " ", description = " ")
    private String password;

    @Column(name = "user_name", nullable = false, length = 100)
    @Schema(required = true, title = "사용자 닉네임", example = " ", description = " ")
    private String nickName;

    @Column(name = "state", nullable = true, length = 1)
    @Enumerated(EnumType.STRING)
    @Schema(required = false, title = "상태코드", example = " ", description = " ")
    private StateCd state;

    /* -------------------------------------------------------------------------------------------------------- */
    /* Spring Security의 UserDetails 구현 함수들..
    /* -------------------------------------------------------------------------------------------------------- */

    /**
     * 권한 (기본 권한 셋팅)
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    /**
     * 계정 ID
     *
     * @return
     */
    @Override
    public String getUsername() {
        return this.userId;
    }

    /**
     * 계정 만료
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        if (state.equals(StateCd.A))
            return false;
        return true;
    }

    /**
     * 잠긴 계정
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        if (state.equals(StateCd.L))
            return false;
        return true;
    }

    /**
     * 패스워드 만료
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        if (state.equals(StateCd.P))
            return false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정만료/계정잠김/패스워드만료 상태가 모두 정상이면..
        if (isCredentialsNonExpired() && isAccountNonExpired() && isAccountNonLocked())
            return true;

        return false;
    }
}
