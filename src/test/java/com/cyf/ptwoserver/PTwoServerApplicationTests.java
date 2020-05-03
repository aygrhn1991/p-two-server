package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import com.cyf.ptwoserver.wx.util.UtilMain;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

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
        String s = this.utilMain.get_authorizer_access_token("wx7d04f2e04d2c7dad");
        System.out.println(s);

    }

}
