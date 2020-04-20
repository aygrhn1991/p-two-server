package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.wx.models.WxConfig;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/main")
public class MainCtrl {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxConfig wxConfig;

    @RequestMapping("/event")
    @ResponseBody
    public String event(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(wxConfig.appId);
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String encrypt_type = request.getParameter("encrypt_type");
        String msg_signature = request.getParameter("msg_signature");
        logger.info(String.format("component_verify_ticket推送参数：[timestamp=%s],[nonce=%s],[encrypt_type=%s],[msg_signature=%s]", timestamp, nonce, encrypt_type, msg_signature));
        try {
            String text = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            logger.info(String.format("component_verify_ticket推送解密前明文：%s", text));

//            String appId = "wxf5d8d7427ebabd90";
//            String encodingAesKey = "ef8dgq54regx9saol27z1bszubanp6knao09pg31h7l";
//            String token = "hello";
//            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
//            String result = pc.decryptMsg(msg_signature, timestamp, nonce, text);
//            System.out.println("解密后明文: " + result);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            StringReader sr = new StringReader(result);
//            InputSource is = new InputSource(sr);
//            Document document = db.parse(is);
//            Element root = document.getDocumentElement();
//            NodeList nodelist1 = root.getElementsByTagName("AppId");
//            NodeList nodelist2 = root.getElementsByTagName("CreateTime");
//            NodeList nodelist3 = root.getElementsByTagName("InfoType");
//            NodeList nodelist4 = root.getElementsByTagName("ComponentVerifyTicket");
//            String AppId = nodelist1.item(0).getTextContent();
//            String CreateTime = nodelist2.item(0).getTextContent();
//            String InfoType = nodelist3.item(0).getTextContent();
//            String ComponentVerifyTicket = nodelist4.item(0).getTextContent();
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String timeText = format.format(new Date());
//            String sql = "INSERT INTO ticket VALUES(?,?,?,?,?,?)";
//            int count = this.jdbcTemplate.update(sql, new Object[]{AppId, timeText, InfoType, ComponentVerifyTicket});
//            System.out.println("存储结果：" + count);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "success";
//        String AppId = request.getParameter("AppId");
//        String CreateTime = request.getParameter("CreateTime");
//        String InfoType = request.getParameter("InfoType");
//        String ComponentVerifyTicket = request.getParameter("ComponentVerifyTicket");

//        if (request.getMethod().toLowerCase().equals("get")) {
//            String timestamp = request.getParameter("timestamp");
//            String nonce = request.getParameter("nonce");
//            String signature = request.getParameter("signature");
//            String echostr = request.getParameter("echostr");
//            return WxUtil.checkConfig(wxToken, timestamp, nonce, signature) ? echostr : null;
//        } else {
//            return null;
//        }
    }

}
