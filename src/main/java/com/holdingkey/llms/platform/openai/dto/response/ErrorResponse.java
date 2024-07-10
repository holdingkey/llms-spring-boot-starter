package com.holdingkey.llms.platform.openai.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private String message;

    private String type;

    private String param;

    private String code;

}
