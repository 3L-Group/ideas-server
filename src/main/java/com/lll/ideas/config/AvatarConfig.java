package com.lll.ideas.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author lbh
 * @Date 2021/3/14
 */
@Configuration
public class AvatarConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**").addResourceLocations(
            "file:" +System.getProperty("user.dir")
                    +System.getProperty("file.separator")
                    +"src"
                    +System.getProperty("file.separator")
                    +"resources"
                    +System.getProperty("file.separator")
                    +"avatar"
                    +System.getProperty("file.separator")
        );
    }
}
