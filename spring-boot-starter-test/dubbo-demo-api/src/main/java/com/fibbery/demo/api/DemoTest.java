package com.fibber.demo.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fibbery.demo.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;

/**
 * @author fibbery
 * @date 18/7/2
 */
@Controller
@RequestMapping("/demo")
public class DemoTest {

    @Reference
    private DemoService demoService;

    @RequestMapping("/test")
    @ResponseBody
    public Host test() {
        Host host = new Host();
        host.setHost("demo");
        host.setIp(demoService.getRemoteIp());
        return host;
    }

    public class Host implements Serializable {

        private static final long serialVersionUID = -3639526575776181835L;

        private String host;

        private String ip;

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }
    }
}
