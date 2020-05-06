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

    @Autowired
    private com.cyf.ptwoserver.wx.mapper.main.component_verify_ticket_mapper component_verify_ticket_mapper;

    @Test
    void contextLoads() {
        Map map = new HashMap();
        map.put("appid", "wx9a6fb162ec18f8e3");
        map.put("open_appid", this.wxConfig.appId);
        String str = new RestTemplate().postForObject("https://api.weixin.qq.com/cgi-bin/open/bind?access_token=" + this.utilMain.get_component_access_token(), map, String.class);
//        user_info ui = this.utilSub.get_user_info("wx0969469f30ac0f3f", "o9QXJ1UTi0Al6xMlKOY5qvIXPKwY");
        System.out.println("test.result----->" + str);

    }

}
