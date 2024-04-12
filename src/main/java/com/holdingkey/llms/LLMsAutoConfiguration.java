package com.holdingkey.llms;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.openai.OpenAIPlatform;
import com.holdingkey.llms.platform.openai.chat.OpenAIChat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Configuration
public class LLMsAutoConfiguration {

    @Resource
    private LLMsProperties properties;

    @Bean
    public OpenAIPlatform openAIPlatform() {
        OpenAIPlatform platform = new OpenAIPlatform();

        platform.setProperties(properties);

        platform.setPlatform("OpenAi");

        OpenAIChat chat = new OpenAIChat();
        chat.setPlatform(platform);

        platform.setOpenAIChat(chat);
        return platform;
    }

    @Bean
    public OpenAIPlatform api2dPlatform() {
        OpenAIPlatform platform = new OpenAIPlatform();

        platform.setProperties(properties);

        platform.setPlatform("Api2d");

        OpenAIChat chat = new OpenAIChat();
        chat.setPlatform(platform);

        platform.setOpenAIChat(chat);
        return platform;
    }

    @Bean
    public LLMsTemplate openAITemplate(List<? extends IPlatform> platformList) {
        LLMsTemplate template = new LLMsTemplate();
        template.setPlatformList(new CopyOnWriteArrayList<>());
        platformList.forEach(template.getPlatformList()::add);
        return template;
    }

}
