package com.chd.demomail;

import com.chd.demomail.Util.MailUtil;
import com.chd.demomail.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoMailApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {
        MailUtil.sendSimpleMail("jinglong-fan@chd.com.cn","标题","内容");
        log.info("发送邮件成功");
    }

}
