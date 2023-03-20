package com.lyk.ssl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: HelloVO
 * @Author: lequal
 * @Date: 2023/03/20
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloVO {

    private String name;

    private String greetings;
}
