package com.holdingkey.llms.platform.openai.mode;

import cn.hutool.json.JSONUtil;
import com.holdingkey.llms.LLMsProperties;
import com.holdingkey.llms.basic.mode.IChatCompletions;
import com.holdingkey.llms.platform.openai.dto.request.ChatRequest;
import com.holdingkey.llms.platform.openai.dto.request.MessageRequest;
import com.holdingkey.llms.platform.openai.dto.response.ChatResponse;
import com.holdingkey.llms.platform.openai.header.Header;
import lombok.Setter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OpenaiChatCompletions implements IChatCompletions {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenaiChatCompletions.class);

    @Setter
    private LLMsProperties.OpenAiConfig config;

    private OkHttpClient client;

    private static final String MODE = "/v1/chat/completions";

    public OkHttpClient getClient() {
        if (this.client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            // 设置读取超时
            builder.readTimeout(this.config.getReadTimeout(), TimeUnit.SECONDS);
            // 设置连接超时
            builder.connectTimeout(this.config.getConnectTimeout(), TimeUnit.SECONDS);
            // 设置写入超时
            builder.writeTimeout(this.config.getWriteTimeout(), TimeUnit.SECONDS);
            this.client = builder.build();
        }
        return this.client;
    }

    private String getUrl() {
        return this.config.getBasicUrl() + MODE;
    }

    public String send(String body) {
        try {
            Header header = new Header(this.config.getKey(), "application/json");
            MediaType mediaType = MediaType.parse(header.getContentType());
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request request = new Request.Builder()
                    .url(this.getUrl())
                    .post(requestBody)
                    .addHeader("Authorization", header.getAuthorization())
                    .addHeader("Content-Type", header.getContentType())
                    .build();
            Response response = this.getClient().newCall(request).execute();

            if (Objects.nonNull(response.body())) {
                String result = response.body().string();
                LOGGER.info("请求成功，url：{}，返回数据：{}", this.getUrl(), result);
                return result;
            }
        } catch (Exception e) {
            LOGGER.error("请求失败，url：{}", this.getUrl(), e);
        }
        return null;
    }

    @Override
    public String call(String prompt) {
        MessageRequest messageRequest = new MessageRequest().setContent(prompt).setRole("user");
        ChatRequest chatRequest = new ChatRequest()
                .setModel(this.config.getModel())
                .setMessages(Collections.singletonList(messageRequest));
        String body = JSONUtil.toJsonStr(chatRequest);
        String responseStr = this.send(body);
        ChatResponse response = JSONUtil.toBean(responseStr, ChatResponse.class);
        return response.getChoices()[0].getMessage().getContent();
    }

}
