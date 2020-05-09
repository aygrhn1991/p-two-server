package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.util.UtilMain;
import com.cyf.ptwoserver.wx.util.UtilSub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class PTwoServerApplicationTests {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UtilMain utilMain;

    @Autowired
    private UtilSub utilSub;


    @Test
    void contextLoads() {
        this.utilSub.send_custom_service_message_text("wx0969469f30ac0f3f", "o9QXJ1UTi0Al6xMlKOY5qvIXPKwY", "hi!");
        System.out.println("test.result----->");

    }

}
