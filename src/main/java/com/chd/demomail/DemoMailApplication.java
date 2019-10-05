package com.chd.demomail;

import com.chd.demomail.Util.MailUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMailApplication {

    public static void main(String[] args) {

        MailUtil.sendSimpleMail("jinglong-fan@chd.com.cn","标题","内容");
        SpringApplication.run(DemoMailApplication.class, args);
    }

}
