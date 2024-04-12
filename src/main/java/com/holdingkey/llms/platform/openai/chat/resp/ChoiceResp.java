package com.holdingkey.llms.platform.openai.chat.resp;

import lombok.Data;

@Data
public class ChoiceResp {

    private MessageResp message;

    private String finishReason;

    private String index;

}
