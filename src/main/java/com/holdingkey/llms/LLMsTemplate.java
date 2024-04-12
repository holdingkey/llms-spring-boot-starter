package com.holdingkey.llms;

import com.holdingkey.llms.basic.IPlatform;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class LLMsTemplate {

    private static final Logger logger = LoggerFactory.getLogger(LLMsTemplate.class);

    private CopyOnWriteArrayList<IPlatform> platformList;

    public IPlatform platform(String name) {
        for (IPlatform platform : this.platformList) {
            if (platform.getPlatform().equals(name)) {
                return platform;
            }
        }
        return null;
    }

}
