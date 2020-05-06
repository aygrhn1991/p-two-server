package com.cyf.ptwoserver.wx.controller;

import com.cyf.ptwoserver.mapper.activity_mapper;
import com.cyf.ptwoserver.models.activity_user;
import com.cyf.ptwoserver.util.UtilHttp;
import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.mapper.sub.auth_user_info_mapper;
import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.main.auth_event;
import com.cyf.ptwoserver.wx.models.main.component_verify_ticket;
import com.cyf.ptwoserver.wx.models.sub.auth_access_token;
import com.cyf.ptwoserver.wx.models.sub.auth_user_info;
import com.cyf.ptwoserver.wx.models.sub.scope;
import com.cyf.ptwoserver.wx.util.UtilMain;
import com.cyf.ptwoserver.wx.util.UtilSub;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/sub")
public class SubCtrl {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UtilMain utilMain;

    @Autowired
    private UtilSub utilSub;

    @Autowired
    private auth_user_info_mapper auth_user_info_mapper;

    @Autowired
    private activity_mapper activity_mapper;

    @RequestMapping("/auth/{activityid}/{unionid}")
    public String auth(HttpServletRequest request, HttpServletResponse response, @PathVariable("activityid") int activityid, @PathVariable("unionid") String unionid) {
        String redirect_uri = null;
        try {
            redirect_uri = URLEncoder.encode(UtilHttp.getBaseUrl(request) + "/sub/code/" + activityid + "/" + unionid, "utf-8");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "redirect:" + this.utilSub.get_auth_code_url(this.wxConfig.defaultAppId, redirect_uri, scope.snsapi_userinfo.name(), "wxcyf123");
    }

    @RequestMapping("/code/{activityid}/{unionid}")
    public String code(HttpServletRequest request, HttpServletResponse response, @PathVariable("activityid") int activityid, @PathVariable("unionid") String unionid) {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        String appid = request.getParameter("appid");
        logger.info(String.format("auth_code推送，post参数：[code=%s],[state=%s],[appid=%s]", code, state, appid));
        String auth_access_token_str = new RestTemplate().getForObject(String.format("https://api.weixin.qq.com/sns/oauth2/component/access_token?appid=%s&code=%s&grant_type=authorization_code&component_appid=%s&component_access_token=%s", appid, code, this.wxConfig.appId, this.utilMain.get_component_access_token()), String.class);
        logger.info(String.format("auth_access_token请求结果：%s", new Gson().toJson(auth_access_token_str)));
        auth_access_token aat = new Gson().fromJson(auth_access_token_str, auth_access_token.class);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        String auth_user_info_str = restTemplate.getForObject(String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", aat.access_token, aat.openid), String.class);
        logger.info(String.format("auth_user_info请求结果：%s", auth_user_info_str));
        auth_user_info aui = new Gson().fromJson(auth_user_info_str, auth_user_info.class);
        auth_user_info aui2 = this.auth_user_info_mapper.select_openid(aui.openid);
        if (aui2 == null) {
            aui.systime = new Date();
            int count = this.auth_user_info_mapper.insert(aui);
            logger.info(String.format("auth_user_info存储结果：%s", count));
            return "redirect:/sub/auth/" + activityid + "/" + unionid;
        } else {
            String unionid2 = aui2.unionid;
            this.activityHandle(activityid, unionid, aui2.unionid);
            return "redirect:/home/index";
        }
    }

    private void activityHandle(int activityid, String unionid, String user_unionid) {
        if (!user_unionid.equals(unionid)) {
            activity_user aum = this.activity_mapper.select_activity_user_member(activityid, user_unionid);
            //尚未扫过别人的码
            if (aum == null) {
                activity_user au_new = new activity_user(activityid, unionid, user_unionid, 0);
                au_new.systime = new Date();
                int count = this.activity_mapper.insert_activity_user(au_new);
            } else {
                //已经扫码，但还未关注
                if (aum.subscribe == 0) {
                    int count = this.activity_mapper.delete_activity_user_member(activityid, user_unionid);
                    activity_user au_new = new activity_user(activityid, unionid, user_unionid, 0);
                    au_new.systime = new Date();
                    count = this.activity_mapper.insert_activity_user(au_new);
                }
                //已经扫码并关注
                if (aum.subscribe == 1) {
                    //啥也不用干
                }
                //扫码关注后，又取消关注
                if (aum.subscribe == 2) {
                    //啥也不用干
                }
            }
        }
    }

    @RequestMapping("/wx9a6fb162ec18f8e3/event")
    @ResponseBody
    public String event(HttpServletRequest request, HttpServletResponse response) {
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String encrypt_type = request.getParameter("encrypt_type");
        String msg_signature = request.getParameter("msg_signature");
        logger.info(String.format("event_sub推送，post参数：[timestamp=%s],[nonce=%s],[encrypt_type=%s],[msg_signature=%s]", timestamp, nonce, encrypt_type, msg_signature));
        try {
            String before = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
            logger.info(String.format("event_sub推送，解密前：\n%s", before));
            WXBizMsgCrypt pc = new WXBizMsgCrypt(wxConfig.token, wxConfig.encodingAesKey, wxConfig.appId);
            String after = pc.decryptMsg(msg_signature, timestamp, nonce, before);
            logger.info(String.format("event推送，解密后：\n%s", after));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "success";
    }

}
