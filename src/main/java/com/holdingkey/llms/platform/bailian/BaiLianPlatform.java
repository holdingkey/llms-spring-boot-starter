package com.holdingkey.llms.platform.bailian;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.bailian.mode.BaiLianChatCompletions;
import lombok.Data;

@Data
public class BaiLianPlatform implements IPlatform {

    private String platform;

    private BaiLianChatCompletions chatCompletions;

}
