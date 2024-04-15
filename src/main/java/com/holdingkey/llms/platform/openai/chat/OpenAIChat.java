package com.holdingkey.llms.platform.openai.chat;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONUtil;
import com.holdingkey.llms.basic.mode.chat.IChat;
import com.holdingkey.llms.platform.openai.OpenAIPlatform;
import com.holdingkey.llms.platform.openai.chat.req.ChatReq;
import com.holdingkey.llms.platform.openai.chat.resp.ChatResp;
import org.springframework.stereotype.Component;

@Component
public class OpenAIChat implements IChat<OpenAIPlatform, ChatReq, ChatResp> {

    private OpenAIPlatform platform;

    private ChatReq chatReq;

    @Override
    public void setPlatform(OpenAIPlatform platform) {
        this.platform = platform;
    }

    @Override
    public OpenAIChat req(ChatReq chatReq) {
        if (CharSequenceUtil.isBlank(chatReq.getModel())) {
            chatReq.setModel(this.platform.getProperties().getOpenai().getModel());
        }
        this.chatReq = chatReq;
        return this;
    }

    @Override
    public ChatResp send() {
        String body = JSONUtil.toJsonStr(this.chatReq);
        String resp = this.platform.send(body);
        return JSONUtil.toBean(resp, ChatResp.class);
    }

}
