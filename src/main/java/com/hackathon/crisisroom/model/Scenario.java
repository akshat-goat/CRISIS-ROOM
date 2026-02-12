package com.hackathon.crisisroom.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scenario {
    @Id
    private String id;
    private String title;
    @Lob
    private String systemPrompt; // The AI's instructions
    private String initialBootLog;
    private String winningKeyword; // Secret word to win
}
