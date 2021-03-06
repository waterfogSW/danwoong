package com.dku.danwoong;

import com.dku.danwoong.dialogflow.utils.GoogleAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        GoogleAuth.authImplicit();
        SpringApplication.run(Application.class, args);
    }

}
