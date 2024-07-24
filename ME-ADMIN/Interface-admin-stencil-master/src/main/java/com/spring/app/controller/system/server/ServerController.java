package com.spring.app.controller.system.server;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spring.app.response.RespData;
import com.spring.app.response.RespMethod;
import com.spring.app.utils.ServiceMonitoring.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: ServerController
 * @Date: 2023/11/2 11:07
 * @Description:
 **/
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @Autowired
    private RespMethod respMethod;
    /**
     * 获取服务器相关信息
     * @return
     * @throws Exception
     */
    @GetMapping()
    @SaCheckLogin
    public RespData getInfo() throws Exception
    {
        // 获取服务器相关信息
        Server server = new Server();
        server.copyTo();

        return respMethod.inquireSuccess(server);
    }
}
