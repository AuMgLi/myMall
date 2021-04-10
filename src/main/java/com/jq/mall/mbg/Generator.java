package com.jq.mall.mbg;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Generator {
    public static void main(String[] args) throws Exception {
        //MBG 执行过程中的警告信息
        List<String> warnings = new ArrayList<>();

        // 读取MBG配置文件
        // 获取在classpath路径下的资源文件的输入流
        InputStream in = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(in);
        in.close();

        // 当生成的代码重复时，覆盖原代码
        DefaultShellCallback callback = new DefaultShellCallback(true);
        // Create MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        // Exec generated codes
        myBatisGenerator.generate(null);
        // print warnings
        for (String warning : warnings) {
            log.warn(warning);
        }
    }
}
