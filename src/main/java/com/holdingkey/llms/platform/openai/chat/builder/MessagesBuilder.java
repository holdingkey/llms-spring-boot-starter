package com.holdingkey.llms.platform.openai.chat.builder;

import com.holdingkey.llms.platform.openai.chat.enums.Role;
import com.holdingkey.llms.platform.openai.chat.req.MessageReq;

import java.util.ArrayList;
import java.util.List;

public class MessagesBuilder {

    private List<MessageReq> messages;

    public MessagesBuilder system(String content) {
        MessageReq messageReq = MessageReq.builder().role(Role.SYSTEM.toValue()).content(content).build();
        return this.message(messageReq);
    }

    public MessagesBuilder ask(String content) {
        MessageReq messageReq = MessageReq.builder().role(Role.USER.toValue()).content(content).build();
        return this.message(messageReq);
    }

    public MessagesBuilder answer(String content) {
        MessageReq messageReq = MessageReq.builder().role(Role.ASSISTANT.toValue()).content(content).build();
        return this.message(messageReq);
    }

    public MessagesBuilder message(MessageReq messageReq) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(messageReq);
        return this;
    }

    public MessagesBuilder messages(List<MessageReq> messages) {
        if (messages == null) {
            return this;
        }
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.addAll(messages);
        return this;
    }

    public List<MessageReq> build() {
        return this.messages;
    }

}