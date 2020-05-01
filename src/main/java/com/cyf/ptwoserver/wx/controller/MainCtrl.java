package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.util.UtilHttp;
import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.mapper.auth_mapper;
import com.cyf.ptwoserver.wx.mapper.component_verify_ticket_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.auth;
import com.cyf.ptwoserver.wx.models.main.authorization_info;
import com.cyf.ptwoserver.wx.models.main.authorizer_info;
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
    private auth_mapper auth_mapper;

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
            long createTime = Long.parseLong(root.elementText("CreateTime"));
            String infoType = root.elementText("InfoType");
            String componentVerifyTicket = root.elementText("ComponentVerifyTicket");
            component_verify_ticket cvt = new component_verify_ticket(appId, new Date(createTime * 1000), infoType, componentVerifyTicket);
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
        String authorization_code = request.getParameter("auth_code");
        String expires_in = request.getParameter("expires_in");
        logger.info(String.format("auth_code推送，post参数：[auth_code=%s],[expires_in=%s]", authorization_code, expires_in));
        Map map = new HashMap<>();
        map.put("component_appid", wxConfig.appId);
        map.put("authorization_code", authorization_code);
        auth ai = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_query_auth?component_access_token=%s", this.utilMain.get_component_access_token()), map, auth.class);
        logger.info(String.format("authorization_info请求结果：%s", new Gson().toJson(ai)));
        authorization_info info = this.auth_mapper.select_authorization_info(ai.authorization_info.authorizer_appid);
        if (info != null) {
            logger.info("该用户已存在");
        } else {
            ai.authorization_info.systime = new Date();
            int count = this.auth_mapper.insert_authorization_info(ai.authorization_info);
            logger.info(String.format("authorization_info存储结果：%s", count));
            Map m = new HashMap();
            m.put("component_appid", this.wxConfig.appId);
            m.put("authorizer_appid", ai.authorization_info.authorizer_appid);
            auth ai2 = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=%s", this.utilMain.get_component_access_token()), m, auth.class);
            logger.info(String.format("authorizer_info请求结果：%s", new Gson().toJson(ai2)));
            count=this.auth_mapper.update_authorizer_info(ai2.authorizer_info);
            logger.info(String.format("authorizer_info存储结果：%s", count));
        }

//        //wx7d04f2e04d2c7dad
//        //32_ict0v21f6ujWMEIPlpLOTpowznWd-4sXnhCnHLkLvJxBrxfiU0vzF8ir4OQnNCFcFgerBZSiL4q05I13shzGQlwE6jxsUnQxRBgLUSNfZLAO8tNKRi8lbOgcCjV0ZB8qpY-E6wwKERZ4vGyFDGIiAMDJMO
//        //https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=COMPONENT_ACCESS_TOKEN
//        Map map2 = new HashMap<>();
//        map2.put("component_appid", wxConfig.appId);
//        map2.put("authorizer_appid", "wx7d04f2e04d2c7dad");
//        String pac2 = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/component/api_get_authorizer_info?component_access_token=" + this.utilMain.get_component_access_token(), map2, String.class);
//        logger.info("----->>>>>授权账号信息【详细】：" + pac2);
        return "授权成功";
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
