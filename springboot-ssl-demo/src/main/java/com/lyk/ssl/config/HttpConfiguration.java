package com.lyk.ssl.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: HttpConfiguration
 * @Author: lequal
 * @Date: 2023/03/20
 * @Description: 配置：强制将所有Http请求转成Https请求
 * 因为做了配置，所以：
 *  请求http的时候访问端口 8080
 *  请求https的时候访问端口 9980
 *  使用改配置以后，所有http://xxx:8080的请求都自动变为 https://xxx:9980
 */
//@Configuration
@Slf4j
public class HttpConfiguration {

    @Value("${custom.request-type}")
    private String requestType;

    /**
     * 获取配置文件的 custom.http-port 端口号
     */
    @Value("${custom.http-port}")
    private Integer httpPort;

    /**
     * 获取配置文件的 server.port 端口号
     */
    @Value("${server.port}")
    private Integer serverPort;

    /**
     * @Author: lequal
     * @Description 相当于开启多一个端口为 httpPort 的tomcat服务器来接收请求
     * @Date 2023/03/20 12:36
     * @return org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     */
    @Bean
    public TomcatServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }

    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme(requestType);
        // Connector监听的http的端口号，也可以设置为其它端口
        connector.setPort(httpPort);
        connector.setSecure(false);
        // 监听到http的端口号后转向到的https的端口号
        connector.setRedirectPort(serverPort);
        return connector;
    }

}
