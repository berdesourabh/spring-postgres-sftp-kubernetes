package com.sftp.controller;

import com.sftp.SftpConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sftp")
public class SftpController {

    @Autowired
    private SftpConnection connection;

    @GetMapping
    public ResponseEntity<List<String>> scanSftp() {
        List<String> consume = connection.consume();
        return ResponseEntity.ok(consume);
    }
}
