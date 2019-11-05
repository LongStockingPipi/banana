package pers.jason.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pers.jason.core.property.BananaProperties;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/10/29 9:14
 */
@Configuration
@EnableConfigurationProperties({BananaProperties.class})
public class CoreConfig {

}
