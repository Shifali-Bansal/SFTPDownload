package com.newgen.app;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SftpDownloader {
    public static void main(String[] args) throws IOException {
        String host = "your_sftp_host";
        int port = 22;
        String username = "your_username";
        String password = "your_password";
        String remoteFilePath = "/path/on/sftp/server/file.txt";
        String localFolderPath = "path/to/local/folder/";

        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(username, host, port);
            session.setPassword(password);

            // Avoid asking for key confirmation
            session.setConfig("StrictHostKeyChecking", "no");

            session.connect();

            ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            // Download file
            channelSftp.get(remoteFilePath, localFolderPath);

            // Disconnect
            channelSftp.disconnect();
            session.disconnect();

            System.out.println("File downloaded successfully from SFTP server.");
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }
    }
}

