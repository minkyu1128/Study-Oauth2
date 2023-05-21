package com.mk1126sj.oauth.server.auth.controller;

import com.mk1126sj.oauth.server.auth.model.OauthToken;
import kong.unirest.Unirest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 클라이언트가 구현해야할 Controller
 */
@RestController
public class OauthController {

    @Value("${custom.oauth.url}")
    private String SERVER_URL;

    // 클라이언트가 구현해야하는 코드 - 발급 받은 코드로 토큰 발행
    @RequestMapping("/callback")
    public OauthToken.response code(@RequestParam String code) {

        String cridentials = "clientId:secretKey";
        // base 64로 인코딩 (basic auth 의 경우 base64로 인코딩 하여 보내야한다.)
        String encodingCredentials = new String(
                Base64.encodeBase64(cridentials.getBytes())
        );
        String requestCode = code;
        OauthToken.request.accessToken request = new OauthToken.request.accessToken() {{
            setCode(requestCode);
            setGrantType("authorization_code");
            setRedirectUri(String.format("%s/callback", SERVER_URL));
        }};
        // oauth 서버에 http 통신으로 토큰 발행
        OauthToken.response oauthToken = Unirest.post(String.format("%s/oauth/token", SERVER_URL))
                .header("Authorization", "Basic " + encodingCredentials)
                .fields(request.getMapData())
                .asObject(OauthToken.response.class)
                .getBody();
        return oauthToken;
    }
}
