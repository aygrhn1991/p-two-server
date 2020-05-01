package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.util.UtilMain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PTwoServerApplicationTests {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UtilMain utilMain;

    @Test
    void contextLoads() {
        try {
            System.out.println(utilMain.get_component_access_token());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
