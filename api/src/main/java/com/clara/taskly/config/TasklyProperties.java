package com.clara.taskly.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "taskly")
public record TasklyProperties(
        String rolesClaim,
        String corsOrigin
) {}
