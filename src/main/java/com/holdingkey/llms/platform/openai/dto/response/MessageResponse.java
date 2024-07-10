package com.holdingkey.llms.platform.openai.dto.response;

import lombok.Data;

@Data
public class MessageResponse {

    private String role;

    private String content;

}
