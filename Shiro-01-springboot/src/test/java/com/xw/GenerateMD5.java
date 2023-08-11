package com.xw;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenerateMD5 {

    @Test
    public void generateMD5() {
        String str = "123456";
        String algorithmName = "MD5";
        int hashIterations = 1024;
        ByteSource bytes = ByteSource.Util.bytes("Ben");
        SimpleHash simpleHash = new SimpleHash(algorithmName, str, bytes, hashIterations);
        System.out.println(simpleHash);
        // 123456  ->  e4122ce5c69c2d05a72ced06d0997064
    }

}
