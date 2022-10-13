package com.rainy.common.util;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.rainy.common.entity.BaseEntity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;


public class BaseCodeGenerator {

    protected static final String AUTHOR = "rainy";
    protected static final String JDBC_URL = "jdbc:mysql://localhost:13306/weibo-visualization?useUnicode=true&characterEncoding=UTF-8" +
            "&useSSL=false&zeroDateTimeBehavior=convertToNull&";
    protected static final String JDBC_USERNAME = "root";
    protected static final String JDBC_PASSWORD = "123456";

    protected static final String PACKAGE_ENTITY = "entity";
    protected static final String PACKAGE_MAPPER = "mapper";
    protected static final String PACKAGE_SERVICE = "service";
    protected static final String PACKAGE_SERVICE_IMPL = "service.impl";
    protected static final String PACKAGE_CONTROLLER = "controller";

    protected String BASE_PACKAGE;

    protected String PARENT_DIR;
    protected String JAVA_DIR;
    protected String XML_PATH;
    protected String ENTITY_PATH;
    protected String MAPPER_PATH;
    protected String SERVICE_PATH;
    protected String SERVICE_IMPL_PATH;
    protected String CONTROLLER_PATH;

    public BaseCodeGenerator(String basePackage, String moduleName, String javaPackageDir) {
        BASE_PACKAGE = basePackage;
        PARENT_DIR = System.getProperty("user.dir") + String.format("/%s/src/main", moduleName);
        JAVA_DIR = PARENT_DIR + javaPackageDir;
        XML_PATH = PARENT_DIR + "/resources/mapper";
        ENTITY_PATH = JAVA_DIR + "/entity";
        MAPPER_PATH = JAVA_DIR + "/mapper";
        SERVICE_PATH = JAVA_DIR + "/service";
        SERVICE_IMPL_PATH = JAVA_DIR + "/service/impl";
        CONTROLLER_PATH = JAVA_DIR + "/controller";
    }

    protected void CodeGenerate(List<String> databases) {
        FastAutoGenerator.create(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD)
                .globalConfig(builder -> builder
                        .author(AUTHOR)
                        .enableSwagger()
                        .disableOpenDir()
                        .dateType(DateType.TIME_PACK)
                        .commentDate("yyyy-MM-dd hh:mm:ss"))
                .packageConfig(builder -> builder
                        .parent(BASE_PACKAGE)
                        .entity(PACKAGE_ENTITY)
                        .mapper(PACKAGE_MAPPER)
                        .service(PACKAGE_SERVICE)
                        .serviceImpl(PACKAGE_SERVICE_IMPL)
                        .controller(PACKAGE_CONTROLLER)
                        .pathInfo(
                                new HashMap() {{
                                    put(OutputFile.entity, ENTITY_PATH);
                                    put(OutputFile.mapper, MAPPER_PATH);
                                    put(OutputFile.xml, XML_PATH);
                                    put(OutputFile.service, SERVICE_PATH);
                                    put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
                                    put(OutputFile.controller, CONTROLLER_PATH);
                                }}
                        )
                )
                .strategyConfig(builder -> builder
                        .addInclude(databases)
                        // Entity策略配置
                        .entityBuilder()
                        .superClass(BaseEntity.class)
                        .enableChainModel()
                        .enableLombok()
                        .enableTableFieldAnnotation()
                        .addSuperEntityColumns("create_time", "update_time", "is_delete")
                        .idType(IdType.AUTO)
                        // Mapper策略配置
                        .mapperBuilder()
                        .enableMapperAnnotation()
                        .enableBaseResultMap()
                        .enableBaseColumnList()
                        .formatMapperFileName("%sMapper")
                        .formatXmlFileName("%sMapper")
                        // Service策略配置
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        // Controller策略配置
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .build()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}


