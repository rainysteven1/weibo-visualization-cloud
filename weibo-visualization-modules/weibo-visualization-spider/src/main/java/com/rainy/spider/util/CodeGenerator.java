package com.rainy.spider.util;

import com.rainy.common.util.BaseCodeGenerator;

import java.util.LinkedList;
import java.util.List;

public class CodeGenerator extends BaseCodeGenerator {

    protected static final String BASE_PACKAGE = "com.rainy.spider";

    private static final String MODULE_DIR = "weibo-visualization-modules";
    private static final String MODULE_NAME = MODULE_DIR + "/weibo-visualization-spider";
    private static final String JAVA_PACKAGE_DIR = "/java/com/rainy/spider";

    public CodeGenerator() {
        super(BASE_PACKAGE, MODULE_NAME, JAVA_PACKAGE_DIR);
    }

    public static void main(String[] args) {
        List<String> databases = new LinkedList<>();
        databases.add("hot_search");
        CodeGenerator codeGenerator = new CodeGenerator();
        codeGenerator.CodeGenerate(databases);
    }
}