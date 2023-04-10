package com.hhhhhx.mbgl;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

@SpringBootTest(classes = MbglApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CodeProduct {
    /*代码生成器*/
    @Test
    public void mybatis() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mbgl?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("hhhhhx") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            // .outputDir("D:\\Code\\IdeaArea\\小程序后端\\mbgl\\src\\main\\java"); // 指定输出目录
                            .outputDir("D:\\Code\\小程序\\代码生成"); // 追加 不要再原位置
                })
                .packageConfig(builder -> {
                    builder.parent("com.hhhhhx") // 设置父包名
                            .moduleName("mbgl") // 设置父包模块名
                            // .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Code\\IdeaArea\\小程序后端\\mbgl\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Code\\小程序\\代码生成\\mapper"));
                })

                .strategyConfig(builder -> {
                    builder
                            // .addInclude("t_connect") // 设置需要生成的表名
                            // .addInclude("t_doctor")
                            // .addInclude("t_message")
                            // .addInclude("t_patient")
                            // .addInclude("t_record")
                            // .addInclude("t_user")
                            // .addInclude("t_notice")
                            // .addInclude("t_user")
                            // .addInclude("t_charge")
                            // .addInclude("t_drug")
                            // .addInclude("t_order")
                            // .addInclude("t_order_item")
                            // .addInclude("t_stock")
                            // .addInclude("t_prescription")
                            // .addInclude("t_prescription_item")
                            .addInclude("t_drug_type")
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
