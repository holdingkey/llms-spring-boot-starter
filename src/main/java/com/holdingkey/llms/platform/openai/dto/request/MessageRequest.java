package com.holdingkey.llms.platform.openai.dto.request;

import com.holdingkey.llms.basic.dto.request.IRequest;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MessageRequest implements IRequest {

    private String role;

    private String content;

}
