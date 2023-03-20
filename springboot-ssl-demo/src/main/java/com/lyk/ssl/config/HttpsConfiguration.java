package com.lyk.ssl.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HttpsConfiguration
 * @Author: lequal
 * @Date: 2023/03/20
 * @Description: 该配置为Http、Https都开启，也就是http://xxx:8080 || https://xxx:9980都可以请求进来并不会发生强制转换为https请求
 *
 * 如果要求所有http请求都强制转成https，则需要开启 HttpConfiguration这个类的@Configuration注解，并将该类的 @Configuration 注释
 */
@Configuration
public class HttpsConfiguration {

    @Value("${custom.http-port}")
    private Integer httpPort;

    /**
     * @Author: lequal
     * @Description 相当于开启多一个端口为 httpPort 的tomcat服务器来接收请求
     * @Date 2023/03/20 12:35
     * @return org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(httpPort);
        return connector;
    }
}
