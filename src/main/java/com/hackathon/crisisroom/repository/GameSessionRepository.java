package com.hackathon.crisisroom.repository;

import com.hackathon.crisisroom.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface GameSessionRepository extends JpaRepository<GameSession, UUID> {
}
