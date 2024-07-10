package com.holdingkey.llms.platform.openai.header;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Header {

    private String authorization;

    private String contentType;

    public Header(String key, String contentType) {
        this.authorization = "Bearer " + key;
        this.contentType = contentType;
    }

}
