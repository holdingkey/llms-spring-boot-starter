package com.holdingkey.llms.platform.openai.dto.response;

import com.holdingkey.llms.basic.dto.response.IResponse;
import com.holdingkey.llms.platform.openai.enums.Model;
import lombok.Data;

@Data
public class ChatResponse implements IResponse {

    private String id;

    private String object;

    private String created;

    private Model model;

    private ChoiceResponse[] choices;

    private ErrorResponse error;

}
