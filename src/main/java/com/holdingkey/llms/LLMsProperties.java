package com.holdingkey.llms;

import com.holdingkey.llms.basic.IConfig;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.llms")
public class LLMsProperties {

    private Integer readTimeout = 100;

    private Integer connectTimeout = 60;

    private Integer writeTimeout = 60;

    private OpenAiProperties openai;

    @Data
    @Accessors(chain = true)
    public static class OpenAiProperties implements IConfig {

        private String basicUrl;

        private String mode;

        private String model;

        private String key;

    }

}
