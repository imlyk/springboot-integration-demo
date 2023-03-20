package com.lyk.ssl.controller;

import com.lyk.ssl.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName: IndexController
 * @Author: lequal
 * @Date: 2023/03/20
 * @Description: 用于测试的控制器
 */
@RequestMapping("/index")
@RestController
@Slf4j
public class IndexController {

    @Resource
    private IndexService indexService;

    @GetMapping
    public ResponseEntity<?> index() {
        log.info("index 被访问");
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        log.info("hello-1 被访问");
        return indexService.hello();
    }

    @GetMapping("/hello/{name}")
    public ResponseEntity<?> hello(@PathVariable(name = "name") String name) {
        log.info("hello-2 被访问");
        return indexService.hello(name);
    }
}
