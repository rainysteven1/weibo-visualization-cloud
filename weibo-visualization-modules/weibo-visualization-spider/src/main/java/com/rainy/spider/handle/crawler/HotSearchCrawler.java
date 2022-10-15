package com.rainy.spider.handle.crawler;

import com.alibaba.fastjson2.JSON;
import com.rainy.spider.entity.HotSearch;
import com.rainy.spider.handle.threadpool.CustomRejectHandle;
import com.rainy.spider.handle.threadpool.CustomThreadFactory;
import com.rainy.spider.service.HotSearchService;
import com.rainy.spider.util.HttpClientUtil;
import com.sun.java.browser.plugin2.DOM;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static com.rainy.spider.common.SysConstant.*;

@Component
@Slf4j
public class HotSearchCrawler {

    private static final int QUEUE_NUM = 1000;
    private static final int CORE_POOL_SIZE = 50;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 60;

    @Autowired
    private RedisTemplate redisTemplate;

    public List<HotSearch> crawler() {
//        BlockingDeque queue = new LinkedBlockingDeque(QUEUE_NUM);
//        CustomThreadFactory customThreadFactory = new CustomThreadFactory("热搜线程组");
//        CustomRejectHandle customRejectHandle = new CustomRejectHandle();
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME,
//                TimeUnit.SECONDS, queue, customThreadFactory, customRejectHandle);

        log.info("开始热搜爬虫");
        Date startDate = new Date();
        log.info("热搜榜爬虫");
        String hotSearchRankingHtml = HttpClientUtil.sendGet(HOT_SEARCH_RANKING_URL, setHeader(), null);
        Map<Integer, HotSearch> hotSearchMap = parsehotSearchRanking(hotSearchRankingHtml);
        return new ArrayList<>(hotSearchMap.values());

//        CountDownLatch countDownLatch = new CountDownLatch(MAXIMUM_POOL_SIZE);

    }

    private Map<Integer, HotSearch> parsehotSearchRanking(String html) {
        Map<Integer, HotSearch> hotSearchMap = new HashMap<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.select("tr[class=\"\"]");
        for (Element element : elements) {
            Elements rankElement = element.select("td[class=td-01 ranktop]");
            if (!rankElement.isEmpty() && StringUtil.isNumeric(rankElement.text())) {
                Integer hotSearchRank = Integer.parseInt(rankElement.text());
                String hotSearchTitle = element.select("td[class=td-02]").select("a").text();
                String hotSearchHeat = element.select("td[class=td-02]").select("span").text();
                String hotSearchURL = HOT_SEARCH_PREFIX_URL + hotSearchTitle;
                HotSearch hotSearch = HotSearch.builder()
                        .hotSearchRank(hotSearchRank)
                        .hotSearchTitle(hotSearchTitle)
                        .hotSearchHeat(hotSearchHeat)
                        .hotSearchUrl(hotSearchURL)
                        .build();
                hotSearchMap.put(hotSearchRank, hotSearch);
                log.info(JSON.toJSONString(hotSearch));
            }
        }
        Map<Integer, HotSearch> sortedHotSearchMap = hotSearchMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldVal, newVal) -> oldVal,
                                LinkedHashMap::new
                        )
                );
        return sortedHotSearchMap;
    }
}
