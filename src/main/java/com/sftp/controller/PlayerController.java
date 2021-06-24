package com.sftp.controller;

import com.sftp.model.Player;
import com.sftp.repository.PlayerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers() {
        List<Player> players = playerJpaRepository.findAll();
        return ResponseEntity.ok(players);
    }
}
