package com.holdingkey.llms.platform.openai.dto.response;

import lombok.Data;

@Data
public class ChoiceResponse {

    private MessageResponse message;

    private String finishReason;

    private String index;

}
