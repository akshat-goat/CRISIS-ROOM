package com.hackathon.crisisroom.config;

import com.hackathon.crisisroom.model.Scenario;
import com.hackathon.crisisroom.repository.ScenarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final ScenarioRepository repo;

    public DataLoader(ScenarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Scenario(
                "1",
                "CPU MELTDOWN",
                "A process 'rogue-miner' (PID 404) is using 100% CPU. If user types 'top', show it. If user kills it, they win.",
                "CRITICAL ALERT: SERVER UNRESPONSIVE. CPU AT 99%.",
                "kill 404"
        ));
    }
}
