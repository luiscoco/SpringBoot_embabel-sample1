package com.example.embabeldemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.embabel.agent.config.annotation.EnableAgentShell;
import com.embabel.agent.config.annotation.EnableAgents;
import com.embabel.agent.config.annotation.LoggingThemes;

@SpringBootApplication
@EnableAgentShell
@EnableAgents(loggingTheme = LoggingThemes.STAR_WARS)
public class EmbabelDemoApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(EmbabelDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(EmbabelDemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Tip: try: execute \"Say hello to Alice\" (quote your task)");
    }
}
