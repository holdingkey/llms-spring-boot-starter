package com.holdingkey.llms;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({LLMsProperties.class, LLMsAutoConfiguration.class})
public @interface EnableLLMs {
}
