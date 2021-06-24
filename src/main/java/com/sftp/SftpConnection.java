package com.sftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.sftp.SftpProperties.SftpProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.jcraft.jsch.ChannelSftp.LsEntrySelector.CONTINUE;

@Component
@Slf4j
public class SftpConnection {

    @Autowired
    private SftpProps sftpProperties;

    public List<String> consume() {
        try {
            List<String> files = new ArrayList<>();
            log.info(sftpProperties.toString());
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            JSch jSch = new JSch();
            Session session = jSch.getSession(sftpProperties.getUsername(), sftpProperties.getHost(), 22);
            session.setPassword(sftpProperties.getPassword());
            session.setConfig(properties);
            session.connect();
            log.info("Session connected");

            ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            channel.ls(".", lsEntry -> {
                String filename = lsEntry.getFilename();
                files.add(filename);
                return CONTINUE;
            });
            return files;
        } catch (Exception e) {
            log.error("Unable to connect due to {}", e.getMessage());
        }
        return null;
    }


}


