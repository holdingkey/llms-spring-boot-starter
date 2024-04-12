package com.holdingkey.llms.platform.openai.chat.req;

import com.holdingkey.llms.basic.dto.req.IReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageReq implements IReq {

    private String role;

    private String content;

}
