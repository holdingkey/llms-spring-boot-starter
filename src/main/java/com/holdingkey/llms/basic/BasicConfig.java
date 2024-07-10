package com.holdingkey.llms.basic;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BasicConfig {

    private String platform;

}
