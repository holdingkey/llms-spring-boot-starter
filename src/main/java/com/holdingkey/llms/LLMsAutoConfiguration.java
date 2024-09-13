package com.holdingkey.llms;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.bailian.BaiLianPlatform;
import com.holdingkey.llms.platform.bailian.mode.BaiLianChatCompletions;
import com.holdingkey.llms.platform.openai.OpenaiPlatform;
import com.holdingkey.llms.platform.openai.mode.OpenaiChatCompletions;
import com.holdingkey.llms.platform.qianfan.QianfanPlatform;
import com.holdingkey.llms.platform.qianfan.mode.QianfanChatCompletions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Configuration
public class LLMsAutoConfiguration {

    @Resource
    private LLMsProperties properties;

    @Bean
    public List<OpenaiPlatform> openAiPlatform() {
        if (this.properties.getOpenai() == null || this.properties.getOpenai().isEmpty()) {
            return new CopyOnWriteArrayList<>();
        }
        return this.properties.getOpenai().stream().map(openaiConfig -> {
            OpenaiPlatform platform = new OpenaiPlatform();
            platform.setPlatform(openaiConfig.getPlatform());
            // 加载聊天模式
            OpenaiChatCompletions openAiChatCompletions = new OpenaiChatCompletions();
            openAiChatCompletions.setConfig(openaiConfig);
            platform.setChatCompletions(openAiChatCompletions);
            return platform;
        }).collect(Collectors.toList());
    }

    @Bean
    public List<BaiLianPlatform> baiLianPlatform() {
        if (this.properties.getBaiLian() == null || this.properties.getBaiLian().isEmpty()) {
            return new CopyOnWriteArrayList<>();
        }
        return this.properties.getBaiLian().stream().map(baiLianConfig -> {
            BaiLianPlatform platform = new BaiLianPlatform();
            platform.setPlatform(baiLianConfig.getPlatform());
            // 加载聊天模式
            BaiLianChatCompletions baiLianChatCompletions = new BaiLianChatCompletions();
            baiLianChatCompletions.setConfig(baiLianConfig);
            platform.setChatCompletions(baiLianChatCompletions);
            return platform;
        }).collect(Collectors.toList());
    }

    @Bean
    public List<QianfanPlatform> qianfanPlatform() {
        if (this.properties.getQianfan() == null || this.properties.getQianfan().isEmpty()) {
            return new CopyOnWriteArrayList<>();
        }
        return this.properties.getQianfan().stream().map(qianfanConfig -> {
            QianfanPlatform platform = new QianfanPlatform();
            platform.setPlatform(qianfanConfig.getPlatform());
            // 加载聊天模式
            QianfanChatCompletions qianfanChatCompletions = new QianfanChatCompletions();
            qianfanChatCompletions.setConfig(qianfanConfig);
            platform.setChatCompletions(qianfanChatCompletions);
            return platform;
        }).collect(Collectors.toList());
    }

    @Bean
    public LLMsTemplate openAITemplate(List<List<? extends IPlatform>> platformList) {
        LLMsTemplate template = new LLMsTemplate();
        template.setPlatformList(new CopyOnWriteArrayList<>());
        platformList.forEach(template.getPlatformList()::addAll);
        return template;
    }

}
