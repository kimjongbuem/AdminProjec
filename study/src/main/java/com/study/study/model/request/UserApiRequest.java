package com.study.study.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserApiRequest {
    private Long id;

    private String account;

    private String password;

    private String status;

    private String email;

    private String phoneNumber;
}
