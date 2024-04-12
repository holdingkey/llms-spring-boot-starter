package com.holdingkey.llms.platform.openai;


import com.holdingkey.llms.LLMsProperties;
import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.openai.chat.OpenAIChat;
import com.holdingkey.llms.platform.openai.chat.headers.Header;
import lombok.Data;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Data
public class OpenAIPlatform implements IPlatform<LLMsProperties.OpenAiProperties> {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIPlatform.class);

    private String platform;

    private LLMsProperties properties;

    private OpenAIChat openAIChat;

    private OkHttpClient client;

    @Override
    public String getPlatform() {
        return this.platform;
    }

    @Override
    public OpenAIPlatform setConfig(LLMsProperties.OpenAiProperties config) {
        this.properties.setOpenai(config);
        return this;
    }

    @Override
    public OkHttpClient getClient() {
        if (this.client == null) {
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

            // 设置读取超时
            builder.readTimeout(this.properties.getReadTimeout(), TimeUnit.SECONDS);
            // 设置连接超时
            builder.connectTimeout(this.properties.getConnectTimeout(), TimeUnit.SECONDS);
            // 设置写入超时
            builder.writeTimeout(this.properties.getWriteTimeout(), TimeUnit.SECONDS);

            this.client = builder.build();
        }
        return this.client;
    }

    @Override
    public String getUrl() {
        return this.properties.getOpenai().getBasicUrl() + this.properties.getOpenai().getMode();
    }

    @Override
    public OpenAIChat chat() {
        return this.openAIChat;
    }

    @Override
    public String send(String body) {
        try {
            Header header = new Header(this.properties.getOpenai().getKey(), "application/json");
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
                logger.info("请求成功，url：{}，返回数据：{}", this.getUrl(), result);
                return result;
            }
        } catch (Exception e) {
            logger.error("请求失败，url：{}", this.getUrl(), e);
        }
        return null;
    }

}