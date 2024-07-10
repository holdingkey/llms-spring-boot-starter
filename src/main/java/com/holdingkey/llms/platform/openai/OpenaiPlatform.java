package com.holdingkey.llms.platform.openai;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.openai.mode.OpenaiChatCompletions;
import lombok.Data;

@Data
public class OpenaiPlatform implements IPlatform {

    private String platform;

    private OpenaiChatCompletions chatCompletions;

}
