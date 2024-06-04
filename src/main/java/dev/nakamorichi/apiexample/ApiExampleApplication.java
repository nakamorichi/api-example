package dev.nakamorichi.apiexample;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.info.BuildProperties;

import java.util.stream.StreamSupport;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class ApiExampleApplication implements CommandLineRunner {

    private final BuildProperties buildProperties;

    public static void main(String[] args) {
        SpringApplication.run(ApiExampleApplication.class, args);
//        new SpringApplicationBuilder(ApiExampleApplication.class).run(args);
    }

    @Override
    public void run(String[] args) {
        val buildInfo = StreamSupport.stream(buildProperties.spliterator(), false)
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .toList();
        log.info("Application started ({})", buildInfo);
    }
}
