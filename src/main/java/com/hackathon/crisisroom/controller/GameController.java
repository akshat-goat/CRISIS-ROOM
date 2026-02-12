package com.hackathon.crisisroom.controller;

import com.hackathon.crisisroom.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allows frontend to talk to backend
@RequiredArgsConstructor
public class GameController {
    private final GameService service;

    @PostMapping("/start")
    public UUID start() {
        return service.startSession("1").getId(); // Starts Scenario 1
    }

    @PostMapping("/command")
    public String command(@RequestParam UUID sessionId, @RequestBody String command) {
        return service.processCommand(sessionId, command);
    }
}
