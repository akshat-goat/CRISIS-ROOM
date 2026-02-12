package com.hackathon.crisisroom.service;

import com.hackathon.crisisroom.model.*;
import com.hackathon.crisisroom.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GameService {
    private final ScenarioRepository scenarioRepo;
    private final GameSessionRepository sessionRepo;
    private final ChatClient.Builder chatClientBuilder;

    public GameSession startSession(String scenarioId) {
        Scenario scenario = scenarioRepo.findById(scenarioId).orElseThrow();
        GameSession session = new GameSession();
        session.setScenarioId(scenarioId);
        session.setHistory("SYSTEM ALERT: " + scenario.getInitialBootLog());
        return sessionRepo.save(session);
    }

    public String processCommand(UUID sessionId, String command) {
        GameSession session = sessionRepo.findById(sessionId).orElseThrow();
        Scenario scenario = scenarioRepo.findById(session.getScenarioId()).orElseThrow();

        // 1. Check Win
        if (command.contains(scenario.getWinningKeyword())) {
            session.setActive(false);
            sessionRepo.save(session);
            return "SUCCESS: SYSTEM RESTORED. THREAT ELIMINATED.";
        }

        // 2. Call AI
        ChatClient client = chatClientBuilder.build();
        String prompt = "You are a Linux Terminal. Scenario: " +
                scenario.getSystemPrompt() +
                "\nHistory: " + session.getHistory() +
                "\nUser Command: " + command +
                "\nOutput: ONLY the terminal response.";
        String response = client.prompt().user(prompt).call().content();

        // 3. Save History
        session.setHistory(session.getHistory() + "\n> " + command + "\n" + response);
        sessionRepo.save(session);
        return response;
    }
}
