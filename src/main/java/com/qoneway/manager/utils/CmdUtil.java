package com.qoneway.manager.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class CmdUtil {

    private String USER_NAME = null;

    private String PASS_WORD = null;

    private String IP_ADDRESS = null;

    private int IP_PORT = 0;

    private Session session = null;

    public CmdUtil() {
    }

    public CmdUtil(String USER_NAME, String PASS_WORD, String IP_ADDRESS, int IP_PORT) {
        this.USER_NAME = USER_NAME;
        this.PASS_WORD = PASS_WORD;
        this.IP_ADDRESS = IP_ADDRESS;
        this.IP_PORT = IP_PORT;
    }

    private void connect() throws Exception {
        if (StringUtil.isBlank(USER_NAME)) {
            throw new RuntimeException("no usr name");
        }
        if (StringUtil.isBlank(PASS_WORD)) {
            throw new RuntimeException("no password");
        }
        if (StringUtil.isBlank(IP_ADDRESS)) {
            throw new RuntimeException("no ip adress");
        }
        JSch jsch = new JSch();
        session = jsch.getSession(USER_NAME, IP_ADDRESS, IP_PORT);
        session.setPassword(PASS_WORD);

        Properties prop = new Properties();
        prop.setProperty("StrictHostKeyChecking", "no");

        session.setConfig(prop);
        session.connect();
    }

    public String execmd(String cmd) throws Exception {
        if (session == null) {
            connect();
        }
        BufferedReader br = null;
        Channel channel = null;

        if (cmd == null || cmd.trim().equals("")) {
            throw new RuntimeException("指令为空");
        }

        channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(cmd);

        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        channel.connect();
        InputStream in = channel.getInputStream();
        br = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));

        String buf = null;
        StringBuilder builder = new StringBuilder();
        while ((buf = br.readLine()) != null) {
            System.out.println(buf);
            builder.append(buf);
            builder.append("\n");
        }

        br.close();
        channel.disconnect();
        return builder.toString();
    }

    public void destroy() throws Exception {
        session.disconnect();
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getPASS_WORD() {
        return PASS_WORD;
    }

    public void setPASS_WORD(String PASS_WORD) {
        this.PASS_WORD = PASS_WORD;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }

    public void setIP_ADDRESS(String IP_ADDRESS) {
        this.IP_ADDRESS = IP_ADDRESS;
    }

    public int getIP_PORT() {
        return IP_PORT;
    }

    public void setIP_PORT(int IP_PORT) {
        this.IP_PORT = IP_PORT;
    }
}
