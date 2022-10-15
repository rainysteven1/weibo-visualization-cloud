package com.rainy.spider;

import com.rainy.spider.common.SysConstant;
import com.rainy.spider.handle.crawler.HotSearchCrawler;
import com.rainy.spider.service.HotSearchService;
import com.rainy.spider.util.HttpClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeiboVisualizationSpiderApplicationTests {

    @Autowired
    private HotSearchService hotSearchService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetHotSearch() {
        hotSearchService.crawler();
    }

}
