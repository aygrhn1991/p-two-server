package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.util.UtilDate;
import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private component_verify_ticket_mapper component_verify_ticket_mapper;

    @RequestMapping("/event")
    @ResponseBody
    public String event(HttpServletRequest request, HttpServletResponse response) {
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String encrypt_type = request.getParameter("encrypt_type");
        String msg_signature = request.getParameter("msg_signature");
        logger.info(String.format("component_verify_ticket推送，post参数：[timestamp=%s],[nonce=%s],[encrypt_type=%s],[msg_signature=%s]", timestamp, nonce, encrypt_type, msg_signature));
        try {
            String before = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name());
            logger.info(String.format("component_verify_ticket推送，解密前：\n%s", before));
            WXBizMsgCrypt pc = new WXBizMsgCrypt(wxConfig.token, wxConfig.encodingAesKey, wxConfig.appId);
            String after = pc.decryptMsg(msg_signature, timestamp, nonce, before);
            logger.info(String.format("component_verify_ticket推送，解密后：\n%s", after));
            Document document = DocumentHelper.parseText(after);
            Element root = document.getRootElement();
            String appId = root.elementText("AppId");
            String createTime = root.elementText("CreateTime");
            String infoType = root.elementText("InfoType");
            String componentVerifyTicket = root.elementText("ComponentVerifyTicket");
            component_verify_ticket cvt = new component_verify_ticket();
            cvt.appId = appId;
            cvt.createTime = UtilDate.dateToYYYYMMDDHHMMSS(new Date(Long.parseLong(createTime) * 1000));
            cvt.infoType = infoType;
            cvt.componentVerifyTicket = componentVerifyTicket;
            int count = this.component_verify_ticket_mapper.insert(cvt);
            logger.info(String.format("component_verify_ticket存储结果：%s", count));
        } catch (Exception e) {
            logger.info(e.getMessage());
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
