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
//        System.out.println(new Date(1588352905));
////        try {
////            String json="{\"auth\":{\"authorizer_appid\":\"wx7d04f2e04d2c7dad\",\"authorizer_access_token\":\"32_jLxox3_2qW2b3sCZRMZcO-SW4fTjKvfgh5sXaCiblEZka2HC3YL3ia4n-q3rA8L9ooBhWc1EOh3oYL22WdnSPrfMJrL-5ZPnDxpw6JrY3rFBZIzxPHgm5Rx7ITtROGKOGzlJasc4tzwEjhm7UJObAGDIXC\",\"expires_in\":7200,\"authorizer_refresh_token\":\"refreshtoken@@@NhqlUlda1Znpv0BXoGq1x3h3Dwl3iBvq3hP0bH9ZE40\",\"func_info\":[{\"funcscope_category\":{\"id\":1}},{\"funcscope_category\":{\"id\":15}},{\"funcscope_category\":{\"id\":4}},{\"funcscope_category\":{\"id\":7}},{\"funcscope_category\":{\"id\":2}},{\"funcscope_category\":{\"id\":3}},{\"funcscope_category\":{\"id\":11}},{\"funcscope_category\":{\"id\":6}},{\"funcscope_category\":{\"id\":5}},{\"funcscope_category\":{\"id\":8}},{\"funcscope_category\":{\"id\":13}},{\"funcscope_category\":{\"id\":9}},{\"funcscope_category\":{\"id\":10}},{\"funcscope_category\":{\"id\":12}},{\"funcscope_category\":{\"id\":22}},{\"funcscope_category\":{\"id\":23}},{\"funcscope_category\":{\"id\":26}},{\"funcscope_category\":{\"id\":27},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":33},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":34}},{\"funcscope_category\":{\"id\":35}},{\"funcscope_category\":{\"id\":44},\"confirm_info\":{\"need_confirm\":0,\"already_confirm\":0,\"can_confirm\":0}},{\"funcscope_category\":{\"id\":46}},{\"funcscope_category\":{\"id\":47}},{\"funcscope_category\":{\"id\":54}},{\"funcscope_category\":{\"id\":66}}]}}";
////            auth info=new Gson().fromJson(json, auth.class);
////            System.out.println(info);
////        }catch (Exception e){
////            System.out.println(e.getMessage());
////        }
        try{
            String s="<xml><AppId><![CDATA[wxf5d8d7427ebabd90]]></AppId>\n" +
                    "<CreateTime>1588355523</CreateTime>\n" +
                    "<InfoType><![CDATA[component_verify_ticket]]></InfoType>\n" +
                    "<ComponentVerifyTicket><![CDATA[ticket@@@x8Y8G_2O-EAaoin1sDzHuJT6Q7lClQ4S5c4DB2SidnuChul-1o35h3as9SkBveZDEVFggxJdeatylJDGp9PbWQ]]></ComponentVerifyTicket>\n" +
                    "</xml>";
            Document document = DocumentHelper.parseText(s);
            Element root = document.getRootElement();
            String appId = root.elementText("AppId");
            long createTime = Long.parseLong(root.elementText("CreateTime"));
            String infoType = root.elementText("InfoType");
            String componentVerifyTicket = root.elementText("ComponentVerifyTicket");
            component_verify_ticket cvt = new component_verify_ticket(appId, new Date(createTime * 1000), infoType, componentVerifyTicket);
            cvt.systime = new Date();
            int count = this.component_verify_ticket_mapper.insert(cvt);
            System.out.println(count);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
