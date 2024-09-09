package com.holdingkey.llms;

import com.holdingkey.llms.basic.BasicConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "spring.llms")
public class LLMsProperties {

    private List<OpenAiConfig> openai = new ArrayList<>();

    private List<BaiLianConfig> baiLian = new ArrayList<>();

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class OpenAiConfig extends BasicConfig {

        private Integer readTimeout = 100;

        private Integer connectTimeout = 60;

        private Integer writeTimeout = 60;

        private String basicUrl;

        private String mode;

        private String model;

        private String key;

    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    @Accessors(chain = true)
    public static class BaiLianConfig extends BasicConfig {

        private String appId;

        private String apiKey;

    }

}
