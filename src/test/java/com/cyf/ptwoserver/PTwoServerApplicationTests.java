package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.models.WxConfig;
import com.cyf.ptwoserver.wx.models.sub.source_type;
import com.cyf.ptwoserver.wx.util.UtilMain;
import com.cyf.ptwoserver.wx.util.UtilSub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class PTwoServerApplicationTests {

    @Autowired
    private WxConfig wxConfig;

    @Autowired
    private UtilMain utilMain;

    @Autowired
    private UtilSub utilSub;


    @Test
    void contextLoads() {


        final String filePath = "E:";
        final String fileName = "1.png";

//        //设置请求头
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("multipart/form-data");
//        headers.setContentType(type);

        //设置请求体，注意是LinkedMultiValueMap
        FileSystemResource fileSystemResource = new FileSystemResource(filePath + "/" + fileName);
        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("file", fileSystemResource);
        //form.add("filename",fileName);

//        HttpEntity<MultiValueMap<String, Object>> files = new HttpEntity<>(form, headers);
        String appid = "wx9a6fb162ec18f8e3";
        String result = new RestTemplate().postForObject(String.format("https://api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s", this.utilMain.get_authorizer_access_token(appid), source_type.image.name()), form, String.class);
        //logger.info(String.format("临时素材(图片)上传结果：%s", result));
        System.out.println(result);


    }

}
