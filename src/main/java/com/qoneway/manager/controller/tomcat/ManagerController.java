package com.qoneway.manager.controller.tomcat;

import com.qoneway.manager.domain.base.Result;
import com.qoneway.manager.service.tomcat.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/manage")
public class ManagerController {

    @Resource
    private ManageService manageService;

    @ResponseBody
    @RequestMapping("/getApps")
    public Result getApps() throws Exception {
        List<String> apps = manageService.getApps();
        Result result = new Result();
        result.setSuccess(true);
        result.setData(apps);
        return result;
    }

    @ResponseBody
    @RequestMapping("/restart")
    public Result restart(String appName) throws Exception {
        Map<String, Object> map = manageService.handler("restart", appName);
        Result result = new Result();
        result.setSuccess((Boolean) map.get("flag"));
        result.setData(map.get("log"));
        return result;
    }

    @ResponseBody
    @RequestMapping("/redeploy")
    public Result redeploy(String appName) throws Exception {
        Map<String, Object> map = manageService.handler("redeploy", appName);
        Result result = new Result();
        result.setSuccess((Boolean) map.get("flag"));
        result.setData(map.get("log"));
        return result;
    }

    @ResponseBody
    @RequestMapping("/upstatic")
    public Result upstatic(String appName) throws Exception {
        Map<String, Object> map = manageService.handler("upstatic", appName);
        Result result = new Result();
        result.setSuccess((Boolean) map.get("flag"));
        result.setData(map.get("log"));
        return result;
    }
}
