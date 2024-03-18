package com.openclassrooms.nja.chatop.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * The WebConfig class is a configuration class that implements WebMvcConfigurer
 * interface. It provides customization for default Spring MVC configuration.
 * <p>
 * The class specifies the resource handler for serving static resources such as
 * images. The addResourceHandlers method overrides the default resource handler
 * and specifies the URL pattern for resources to be handled and the locations where
 * these resources are located.
 * <p>
 * This class should be used as a configuration class for the Spring MVC application.
 */
@Configuration // Indicates that this class is a configuration class
public class WebConfig implements WebMvcConfigurer { // Implements WebMvcConfigurer to customize default Spring MVC configuration.

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This method overrides the default resource handler, allowing the application to serve static resources.
        registry.addResourceHandler("/images/**") // Specifies the URL pattern for resources to be handled.
                .addResourceLocations("classpath:/images/"); // Specifies where to look for the resources. In this case, it's looking for resources in the 'images' directory located in the classpath (typically inside your project's resources directory).
    }
}
