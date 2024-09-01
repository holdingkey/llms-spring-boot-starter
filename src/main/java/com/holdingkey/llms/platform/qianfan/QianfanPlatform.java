package com.holdingkey.llms.platform.qianfan;

import com.holdingkey.llms.basic.IPlatform;
import com.holdingkey.llms.platform.qianfan.mode.QianfanChatCompletions;
import lombok.Data;

@Data
public class QianfanPlatform implements IPlatform {

    private String platform;

    private QianfanChatCompletions chatCompletions;

}
