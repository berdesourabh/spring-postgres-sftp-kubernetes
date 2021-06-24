package com.sftp.repository;

import com.sftp.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerJpaRepository extends JpaRepository<Player, Long> {
}
