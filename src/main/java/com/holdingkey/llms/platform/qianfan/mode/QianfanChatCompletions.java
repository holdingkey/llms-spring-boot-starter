package com.holdingkey.llms.platform.qianfan.mode;

import com.baidubce.qianfan.Qianfan;
import com.baidubce.qianfan.model.chat.ChatResponse;
import com.holdingkey.llms.LLMsProperties;
import com.holdingkey.llms.basic.mode.IChatCompletions;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Setter
public class QianfanChatCompletions implements IChatCompletions {

    private static final Logger LOGGER = LoggerFactory.getLogger(QianfanChatCompletions.class);

    private LLMsProperties.QianfanConfig config;

    @Override
    public String call(String prompt) {
        ChatResponse response = new Qianfan().chatCompletion()
                .model(this.config.getModel())
                .addMessage("user", prompt)
                .temperature(0.7)
                .execute();
        return response.getResult();
    }

}
