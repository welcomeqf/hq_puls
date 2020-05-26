package com.gpdi.hqplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 实体生成
 *
 * @author lianghb
 * @date 2019-05-23 11:19
 */
public class EntityGenerator {

    public static void main(String[] args) {

        // 开始生成
        System.out.println("回车开始生成...............");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        entityGenerator();

        System.out.println("生成完成");
    }


    public static void entityGenerator() {
        // 这里写你自己的java目录
        String outputDirPath = "/Users/lianghb/work/hq-plus/hqplus-java-2.0/service-resources/src/main/java";
        // 基础包名
        String basePackageName = "com.gpdi.hqplus.bill";
        // 作者
        String author = "lianghb";

        // 数据库
//        String dataBaseUrl = "jdbc:mysql://cdb-6k60thc5.gz.tencentcdb.com:10049/hqplus?useSSL=false&characterEncoding=utf-8";
//        String driverName = "com.mysql.jdbc.Driver";
//        String userName = "root";
//        String password = "jane0513";

        String dataBaseUrl = "jdbc:mysql://172.16.1.73:3306/hqplus?useSSL=false&characterEncoding=utf-8";
        String driverName = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "GPDI@hqplus.510630";

        // 表前缀
        String tablePrefix = "tb_";

        // 需要生成的表名
        String[] tables = new String[]{
                "tb_bill_order"
        };

        // ------------------------------------------------------------------------------------------------------------

        AutoGenerator mpg = new AutoGenerator();


        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDirPath);
        gc.setFileOverride(true);//是否覆盖
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(author);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(dataBaseUrl);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverName);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{tablePrefix});// 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(tables);
        // lombok
        strategy.setEntityLombokModel(true);
        // restController
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(null);
        pc.setEntity(basePackageName + ".entity");
        pc.setMapper(basePackageName + ".mapper");
        pc.setXml("mapper");
        pc.setService(basePackageName + ".service");
        pc.setServiceImpl(basePackageName + ".service.impl");
        pc.setController(basePackageName + ".controller");
        mpg.setPackageInfo(pc);

        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        mpg.setCfg(cfg);


        // 执行生成
        mpg.execute();

        // 打印注入设置
        System.err.println(mpg.getCfg().getMap().get("abc"));
    }
}