package com.holdingkey.llms.platform.bailian.mode;

import com.alibaba.dashscope.app.Application;
import com.alibaba.dashscope.app.ApplicationParam;
import com.alibaba.dashscope.app.ApplicationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.holdingkey.llms.LLMsProperties;
import com.holdingkey.llms.basic.mode.IChatCompletions;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
public class BaiLianChatCompletions implements IChatCompletions {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiLianChatCompletions.class);

    private LLMsProperties.BaiLianConfig config;

    @Override
    public String call(String prompt) {
        ApplicationParam param = ApplicationParam.builder()
                .appId(this.config.getAppId())
                .apiKey(this.config.getApiKey())
                .prompt(prompt)
                .build();
        Application application = new Application();
        try {
            ApplicationResult result = application.call(param);
            return result.getOutput().getText();
        } catch (NoApiKeyException | InputRequiredException e) {
            LOGGER.error("[]", e);
            return null;
        }
    }

}
