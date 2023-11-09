package com.ggteam.single.api.account.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Tag(name = "글로벌 컨트롤러", description = "전체적으로 사용하는 요청")
public class GlobalController {

    @PostMapping("/re-issue")
    @Operation(summary = "엑세스 토큰 재발급", description = "헤더에 Authorization-Refresh 로 Bearer + refreshToken 필요")
    public void reIssueAccessToken(@RequestHeader String refreshToken) {
        // Swagger 확인용
    }
}
