package com.holdingkey.llms.platform.openai.resp;

import com.holdingkey.llms.basic.dto.resp.IResp;
import lombok.Data;

@Data
public class ErrorResp implements IResp {

    private String message;

    private String type;

    private String param;

    private String code;

}
