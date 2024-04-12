package com.holdingkey.llms.basic;

import com.holdingkey.llms.LLMsProperties;
import com.holdingkey.llms.basic.mode.chat.IChat;
import com.holdingkey.llms.platform.openai.OpenAIPlatform;
import okhttp3.OkHttpClient;

public interface IPlatform<Config extends IConfig> {

    String getPlatform();

    void setProperties(LLMsProperties properties);

    OpenAIPlatform setConfig(Config config);

    OkHttpClient getClient();

    String getUrl();

    IChat chat();

    String send(String body);

}
