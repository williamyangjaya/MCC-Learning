package com.mcclearningserver;

import com.mcclearningserver.property.ModuleStorageProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableConfigurationProperties({
    ModuleStorageProperty.class
})
public class MccLearningServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MccLearningServerApplication.class, args);
        System.out.println("===== MCC Learning System =====");
    }

}
