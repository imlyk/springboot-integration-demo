package com.lyk.ssl.service;

import com.lyk.ssl.vo.HelloVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IndexService
 * @Author: lequal
 * @Date: 2023/03/20
 * @Description: 用于测试的业务服务
 */
@Service
public class IndexService {


    public ResponseEntity<?> hello() {
        String hello = "hello, this is ssl test.";
        return new ResponseEntity<>(hello, HttpStatus.OK);
    }


    public ResponseEntity<?> hello(String name) {
        String hello = "hello, %s, this is ssl test.";
        String format = String.format(hello, name);
        HelloVO vo = HelloVO.builder().name(name).greetings(format).build();

        return new ResponseEntity<>(vo, HttpStatus.OK);
    }
}
