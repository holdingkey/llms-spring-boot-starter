package com.holdingkey.llms;

import com.holdingkey.llms.basic.IConfig;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "llms")
public class LLMsProperties {

    private Integer readTimeout = 100;

    private Integer connectTimeout = 60;

    private Integer writeTimeout = 60;

    private OpenAiProperties openai;

    @Data
    @Builder
    public static class OpenAiProperties implements IConfig {

        private String basicUrl;

        private String mode;

        private String model;

        private String key;

    }

}
