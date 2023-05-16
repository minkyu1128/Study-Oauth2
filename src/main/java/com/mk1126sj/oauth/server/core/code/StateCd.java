package com.mk1126sj.oauth.server.core.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 회원상태 코드
 */
@RequiredArgsConstructor
public enum StateCd {
    Y("정상 회원"),
    L("잠긴 계정"),
    P("패스워드 만료"),
    A("계정 만료");

    @Getter
    private final String codeNm;

}
