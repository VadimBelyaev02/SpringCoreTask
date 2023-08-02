package com.vadim.springtask.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.pagination")
public class PaginationProperties {

    @NotNull
    private Integer defaultPageValue;

    @NotNull
    private Integer defaultPageSize;
}

