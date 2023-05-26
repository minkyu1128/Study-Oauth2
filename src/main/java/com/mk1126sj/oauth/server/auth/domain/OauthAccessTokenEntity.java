//package com.mk1126sj.oauth.server.auth.domain;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.RequiredArgsConstructor;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Lob;
//import javax.persistence.Table;
//
//@Entity
//@Data
//@RequiredArgsConstructor
//@AllArgsConstructor
//@Table(name = "oauth_access_token")
//@Schema(name = "OauthAccessTokenEntity", description = "Spring Oauth AccessToken")
//public class OauthAccessTokenEntity {
//    @Id
//    private String authenticationId;
//    private String tokenId;
//    @Lob
//    private byte[] token;
//    private String userName;
//    private String clientId;
//    @Lob
//    private byte[] authentication;
//    private String refreshToken;
//
//}
