package com.demo.dev;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Hello world!
 *
 */
@EnableSwagger2
@SpringBootApplication
public class Application {
	
	protected static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        try {
			Runtime.getRuntime().exec(new String[] { "C:\\Program Files\\Mozilla Firefox\\firefox.exe",
					"http://localhost:8080/swagger-ui.html" });
			logger.info("程序启动成功");
		} catch (IOException e) {
			e.printStackTrace();
		} 
    	
    	
    }
}


