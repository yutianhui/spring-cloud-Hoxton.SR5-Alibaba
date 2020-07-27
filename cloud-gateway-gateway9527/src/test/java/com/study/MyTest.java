package com.study;

import java.time.ZonedDateTime;

/**
 * @ClassName MyTest
 * @Author Yutian Hui
 * @date 2020.07.21 22:25
 */
public class MyTest {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
        // 2020-07-21T22:25:57.729+08:00[Asia/Shanghai]
    }
}
