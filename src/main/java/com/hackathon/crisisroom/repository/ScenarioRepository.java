package com.hackathon.crisisroom.repository;

import com.hackathon.crisisroom.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScenarioRepository extends JpaRepository<Scenario, String> {
}
