package com.holdingkey.llms.platform.openai.chat.resp;

import com.holdingkey.llms.basic.dto.resp.IResp;
import com.holdingkey.llms.platform.openai.chat.enums.Model;
import com.holdingkey.llms.platform.openai.resp.ErrorResp;
import lombok.Data;

@Data
public class ChatResp implements IResp {

    private String id;

    private String object;

    private String created;

    private Model model;

    private ChoiceResp[] choices;

    private ErrorResp error;

}
