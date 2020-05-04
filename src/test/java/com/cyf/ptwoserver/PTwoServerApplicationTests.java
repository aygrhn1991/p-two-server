package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.auth;
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

    @Autowired
    private com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper component_verify_ticket_mapper;

    @Test
    void contextLoads() {
        auth s = this.utilMain.get_authorizer_info("wx170d15e9becda2ed");
        System.out.println(s);

    }

}
