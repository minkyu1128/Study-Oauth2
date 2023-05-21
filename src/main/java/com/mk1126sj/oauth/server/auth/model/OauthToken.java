package com.mk1126sj.oauth.server.auth.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@Schema(name = "OAuth 토큰 정보")
public class OauthToken {


    /**
     * Request 클래스
     */
    @Data
    public static class request {
        /**
         * 엑세스 토큰
         */
        @Data
        public static class accessToken {
            @JsonProperty("code")
            public String code;
            @JsonProperty("grant_type")
            private String grantType;
            @JsonProperty("redirect_uri")
            private String redirectUri;

            public Map getMapData() {
                Map map = new HashMap();
                map.put("code", code);
                map.put("grant_type", grantType);
                map.put("redirect_uri", redirectUri);
                return map;
            }
        }

        /**
         * 리프레시 토큰
         */
        @Data
        public static class refreshToken {
            @JsonProperty("refresh_token")
            private String refreshToken;
            @JsonProperty("grant_type")
            private String grantType;

            public Map getMapData() {
                Map map = new HashMap();
                map.put("refresh_token", refreshToken);
                map.put("grant_type", grantType);
                return map;
            }
        }
    }

    /**
     * Response 클래스
     */
    @JsonIgnoreProperties(ignoreUnknown = true) //deserialize 시 정의되지 않은 field가 있을 경우 무시(ignore)
    @Data
    public static class response {
        @JsonProperty("access_token")
        private String access_token;
        @JsonProperty("token_type")
        private String token_type;
        @JsonProperty("refresh_token")
        private String refresh_token;
        @JsonProperty("expires_in")
        private Long expires_in;
        @JsonProperty("scope")
        private String scope;
    }
}
