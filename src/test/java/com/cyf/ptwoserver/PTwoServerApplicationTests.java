package com.cyf.ptwoserver;

import com.cyf.ptwoserver.wx.lib.main.sec.WXBizMsgCrypt;
import com.cyf.ptwoserver.wx.models.WxConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PTwoServerApplicationTests {

    @Autowired
    private WxConfig wxConfig;

    @Test
    void contextLoads() {
        try {
            //WXBizMsgCrypt pc = new WXBizMsgCrypt(wxConfig.token, wxConfig.encodingAesKey, wxConfig.appId);
//            String after = pc.decryptMsg("f612b89280b98a3c8f02893e41a2b2a8bd905ba4",
//                    "1587365053",
//                    "1979811284",
//                    "<xml>\n" +
//                            "    <AppId><![CDATA[wxf5d8d7427ebabd90]]></AppId>\n" +
//                            "    <Encrypt><![CDATA[kJ9yf8rWmbEkYanr4Atqtk16LVLK5rbjIqrwMAaaxuc+HLef9LMQ/dDiEmA7yST8eKJd+5l91FazaopJODS2SqgwvmOeFfqy1EKsG64XYQKem6B6S8bCx/H6NWgwBVvmQLqN7jAxGtwwb4CWAcvPrCkFXhaZOlnZbvnaq08qYElwZII/nxoGwFY/b4BzIHKhfAZBArme6/WyJWMS0dAB/2GTkDekAenhZAKrjn+Ly5qqcQLJXG63YYtMnpTh9n41odwIUI0N+IFcCgfa9xsbkBDKS/Ko98zHClUDf2qJ8TTxeDaX+0sLtCohMrtS7kuC4EpbXco2EIkYgtK6rxY93oH8bHf3s5yjuj1czG5es5GD37KWeXdN1btIM7i07Nt2ggKAsnSafNNpcgNfsVBOdCkmXIUN+qfbWAB97ZMSX5y7SxhnE69fKIRG5diwafiNGVGkBxaQTo9jBqVlIcC+Hg==]]></Encrypt>\n" +
//                            "</xml>");
            WXBizMsgCrypt pc = new WXBizMsgCrypt("pamtest",
                    "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG",
                    "wxb11529c136998cb6");
            String after = pc.decryptMsg("jn1L23DB+6ELqJ+6bruv21Y6MD7KeIfP82D6gU39rmkgczbWwt5+3bnyg5K55bgVtVzd832WzZGMhkP72vVOfg==",
                    "1409304348",
                    "xxxxxx",
                    "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[jn1L23DB+6ELqJ+6bruv21Y6MD7KeIfP82D6gU39rmkgczbWwt5+3bnyg5K55bgVtVzd832WzZGMhkP72vVOfg==]]></Encrypt></xml>");
            System.out.println(after);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
