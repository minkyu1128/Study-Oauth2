package com.mk1126sj.oauth.server.core.config;

import com.mk1126sj.oauth.server.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * Oauth2 Server Config
 */
@EnableAuthorizationServer
@Configuration
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${custom.oauth.url}")
    private String SERVER_URL;

    @Autowired
    private UserService userService;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * Client 설정
     *
     * @param clients the client details configurer
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory() // 클라이언트 정보는 메모리를 이용 한다.
                .withClient("clientId") // 클라이언트 아이디
                .secret("{noop}secretKey") // 시크릿키 ({} 안에 암호화 알고리즘을 명시 하면 된다. 암호화가 되어 있지 않다면 {noop}로 설정 해야 한다. 실제 요청은 암호화 방식인 {noop}를 입력 하지 않아도 된다.)
                .authorizedGrantTypes("authorization_code", "password", "refresh_token") // 가능한 토큰 발행 타입
                .scopes("read", "write") // 가능한 접근 범위
                .accessTokenValiditySeconds(60) // 토큰 유효 시간 : 1분
                .refreshTokenValiditySeconds(60 * 60) // 토큰 유효 시간 : 1시간
                .redirectUris(String.format("%s/callback", SERVER_URL)) // 가능한 redirect uri
                .autoApprove(true); // 권한 동의는 자동으로 yes (false 로 할시 권한 동의 여부를 묻는다.)
    }


    /**
     * 인증, 토큰 설정
     *
     * @param endpoints the endpoints configurer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.userDetailsService(userService); // refresh token 발행시 유저 정보 검사 하는데 사용하는 서비스 설정
    }

}
