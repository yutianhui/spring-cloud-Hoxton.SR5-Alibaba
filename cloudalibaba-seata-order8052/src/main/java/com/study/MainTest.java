package com.study;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;

/**
 * @ClassName MainTest
 * @Author Yutian Hui
 * @date 2020.07.27 00:19
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println(
                UUID.randomUUID().toString()
        );

        System.out.println("使用雪花算法生成唯一ID");

        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        System.out.println(id + "\t" + String.valueOf(id).length());
        /*
        * 1287436142920208384
        * 1287436231608766464
        * */
    }
}
