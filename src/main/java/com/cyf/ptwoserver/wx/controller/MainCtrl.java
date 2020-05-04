package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.util.UtilHttp;
import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.mapper.auth_event_mapper;
import com.cyf.ptwoserver.wx.mapper.authorization_info_mapper;
import com.cyf.ptwoserver.wx.mapper.authorizer_info_mapper;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.auth;
import com.cyf.ptwoserver.wx.models.main.auth_event;
import com.cyf.ptwoserver.wx.models.main.authorization_info;
import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import com.cyf.ptwoserver.wx.util.UtilMain;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
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
    private auth_event_mapper auth_event_mapper;

    @Autowired
    private authorization_info_mapper authorization_info_mapper;

    @Autowired
    private authorizer_info_mapper authorizer_info_mapper;

    @Autowired
    private UtilMain utilMain;

    @RequestMapping("/event")
    @ResponseBody
    public String event(HttpServletRequest request, HttpServletResponse response) {
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String encrypt_type = request.getParameter("encrypt_type");
        String msg_signature = request.getParameter("msg_signature");
        logger.info(String.format("event推送，post参数：[timestamp=%s],[nonce=%s],[encrypt_type=%s],[msg_signature=%s]", timestamp, nonce, encrypt_type, msg_signature));
        try {
            String before = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8.name());
            logger.info(String.format("event推送，解密前：\n%s", before));
            WXBizMsgCrypt pc = new WXBizMsgCrypt(wxConfig.token, wxConfig.encodingAesKey, wxConfig.appId);
            String after = pc.decryptMsg(msg_signature, timestamp, nonce, before);
            logger.info(String.format("event推送，解密后：\n%s", after));
            Document document = DocumentHelper.parseText(after);
            Element root = document.getRootElement();
            String appId = root.elementText("AppId");
            Date createTime = new Date(Long.parseLong(root.elementText("CreateTime")) * 1000);
            String infoType = root.elementText("InfoType");
            int count = 0;
            switch (infoType) {
                case "component_verify_ticket":
                    String componentVerifyTicket = root.elementText("ComponentVerifyTicket");
                    component_verify_ticket cvt = new component_verify_ticket(appId, createTime, infoType, componentVerifyTicket);
                    cvt.systime = new Date();
                    count = this.component_verify_ticket_mapper.insert(cvt);
                    logger.info(String.format("component_verify_ticket存储结果：%s", count));
                    break;
                case "unauthorized":
                case "updateauthorized":
                case "authorized":
                    String authorizerAppid = root.elementText("AuthorizerAppid");
                    String authorizationCode = root.elementText("AuthorizationCode");
                    Date authorizationCodeExpiredTime = root.elementText("AuthorizationCodeExpiredTime") == null ? null : new Date(Long.parseLong(root.elementText("AuthorizationCodeExpiredTime")) * 1000);
                    String preAuthCode = root.elementText("PreAuthCode");
                    auth_event ae = new auth_event(appId, createTime, infoType, authorizerAppid, authorizationCode, authorizationCodeExpiredTime, preAuthCode);
                    ae.systime = new Date();
                    count = this.auth_event_mapper.insert(ae);
                    logger.info(String.format("auth_event存储结果：%s", count));
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "success";
    }

    @RequestMapping("/code")
    @ResponseBody
    public String code(HttpServletRequest request, HttpServletResponse response) {
        String authorization_code = request.getParameter("auth_code");
        String expires_in = request.getParameter("expires_in");
        logger.info(String.format("auth_code推送，post参数：[auth_code=%s],[expires_in=%s]", authorization_code, expires_in));
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        map.put("authorization_code", authorization_code);
        auth auth = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%s", this.utilMain.get_component_access_token()), map, auth.class);
        logger.info(String.format("authorization_info请求结果：%s", new Gson().toJson(auth)));
        authorization_info info = this.authorization_info_mapper.select(auth.authorization_info.authorizer_appid);
        if (info != null) {
            return "该账号已授权";
        } else {
            auth.authorization_info.systime = new Date();
            int count = this.authorization_info_mapper.insert(auth.authorization_info);
            logger.info(String.format("authorization_info存储结果：%s", count));
            auth auth2 = this.utilMain.get_authorizer_info(auth.authorization_info.authorizer_appid);
            count = this.authorizer_info_mapper.insert(auth2.authorizer_info);
            logger.info(String.format("authorizer_info存储结果：%s", count));
            return "授权成功";
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/geturl")
    @ResponseBody
    public Map geturl(HttpServletRequest request, HttpServletResponse response, @RequestParam("type") String type) {
        String redirect_uri = UtilHttp.getBaseUrl(request) + "/main/code";
        Map map = new HashMap();
        if (type.equals("pc")) {
            map.put("data", this.utilMain.get_auth_code_url_pc(redirect_uri));
            return map;
        }
        if (type.equals("mobile")) {
            map.put("data", this.utilMain.get_auth_code_url_mobile(redirect_uri));
            return map;
        }
        return null;
    }

}
