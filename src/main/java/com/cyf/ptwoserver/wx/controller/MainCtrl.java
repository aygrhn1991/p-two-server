package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.util.UtilDate;
import com.cyf.ptwoserver.util.UtilHttp;
import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import com.cyf.ptwoserver.wx.models.main.pre_auth_code;
import com.cyf.ptwoserver.wx.util.UtilMain;
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
import org.springframework.web.client.RestTemplate;
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
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/main")
public class MainCtrl {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private component_verify_ticket_mapper component_verify_ticket_mapper;

    @Autowired
    private UtilMain utilMain;

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
            component_verify_ticket cvt = new component_verify_ticket(appId, UtilDate.dateToYYYYMMDDHHMMSS(new Date(Long.parseLong(createTime) * 1000)), infoType, componentVerifyTicket);
            cvt.systime = new Date();
            int count = this.component_verify_ticket_mapper.insert(cvt);
            logger.info(String.format("component_verify_ticket存储结果：%s", count));
        } catch (Exception e) {
            logger.error(e.getMessage());
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

    @RequestMapping("/code")
    @ResponseBody
    public String code(HttpServletRequest request, HttpServletResponse response) {
        String auth_code = request.getParameter("auth_code");
        String expires_in = request.getParameter("expires_in");
        logger.info(String.format("auth_code推送，post参数：[auth_code=%s],[expires_in=%s]", auth_code, expires_in));
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        map.put("authorization_code", auth_code);
        RestTemplate restTemplate = new RestTemplate();
        String pac = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=" + this.utilMain.get_component_access_token(), map, String.class);
        logger.info("----->>>>>授权账号信息：" + pac);
        //wx7d04f2e04d2c7dad
        //32_ict0v21f6ujWMEIPlpLOTpowznWd-4sXnhCnHLkLvJxBrxfiU0vzF8ir4OQnNCFcFgerBZSiL4q05I13shzGQlwE6jxsUnQxRBgLUSNfZLAO8tNKRi8lbOgcCjV0ZB8qpY-E6wwKERZ4vGyFDGIiAMDJMO
        //https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=COMPONENT_ACCESS_TOKEN
        Map map2 = new HashMap<>();
        map2.put("component_appid", wxConfig.appId);
        map2.put("authorizer_appid", "wx7d04f2e04d2c7dad");
        String pac2 = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=" + this.utilMain.get_component_access_token(), map2, String.class);
        logger.info("----->>>>>授权账号信息【详细】：" + pac2);
        return "授权成功";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/geturl")
    @ResponseBody
    public Map geturl(HttpServletRequest request, HttpServletResponse response) {
        String redirect_uri = UtilHttp.getBaseUrl(request) + "/main/code";
        Map map = new HashMap();
        map.put("pc", this.utilMain.get_auth_code_url_pc(redirect_uri));
        map.put("mobile", this.utilMain.get_auth_code_url_mobile(redirect_uri));
        return map;
    }

}
