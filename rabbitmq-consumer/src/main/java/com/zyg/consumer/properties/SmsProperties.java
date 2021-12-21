package com.zyg.consumer.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

import java.sql.Struct;
import java.util.Properties;

/**
 * @author 涛哥
 * @date 2021/12/17
 */
@Data
@ConfigurationProperties(prefix = "aliyun")
@Component
public class SmsProperties {
    private String signName;
    private String templateCode;
    private String accessKeyId;
    private String accessKeySecret;
}
