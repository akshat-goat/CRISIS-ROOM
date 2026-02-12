package com.hackathon.crisisroom.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String scenarioId;
    private boolean isActive = true;
    @Lob
    private String history; // Stores chat logs
}
