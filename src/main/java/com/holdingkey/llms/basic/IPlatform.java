package com.holdingkey.llms.basic;

import com.holdingkey.llms.basic.mode.IChatCompletions;

public interface IPlatform {

    String getPlatform();

    IChatCompletions getChatCompletions();

}
