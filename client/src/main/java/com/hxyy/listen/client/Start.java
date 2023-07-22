package com.hxyy.listen.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Start implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Client.connect("ws://108.174.60.150:9090");
    }
}
