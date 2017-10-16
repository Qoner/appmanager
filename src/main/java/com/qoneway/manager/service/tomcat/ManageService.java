package com.qoneway.manager.service.tomcat;

import com.qoneway.manager.utils.CmdUtil;
import com.qoneway.manager.utils.StringUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageService {

    public List<String> getApps() {
        List<String> apps = new ArrayList<>();
        apps.add("eroom");
        return apps;
    }

    public Map<String, Object> handler(String optType, String appName) throws Exception {
        Map<String, Object> data = new HashMap<>();
        String log = null;
        boolean flag = false;
        List<String> apps = getApps();
        if (apps.indexOf(appName) == -1) {
            data.put("log", "该应用不存在");
        } else {
            CmdUtil cmd = new CmdUtil("root", "He258987", "47.93.217.79", 22);
            switch (optType) {
            case "restart":
                log = cmd.execmd("export JRE_HOME=\"/usr/tools/jdk1.8.0_131/jre\" && python3 /root/py/" + appName + "/" + appName + "web.py restart");
                break;
            case "redeploy":
                log = cmd.execmd("export JRE_HOME=\"/usr/tools/jdk1.8.0_131/jre\" && python3 /root/py/" + appName + "/" + appName + "web.py redeploy");
                break;
            case "upstatic":
                log = cmd.execmd("python3 /root/py/" + appName + "/" + appName + "web.py upstatic");
                break;
            }
            if ((!StringUtil.isBlank(log)) && log.contains("operation completed")) {
                flag = true;
            }
        }
        data.put("log", log);
        data.put("flag", flag);
        return data;
    }
}
