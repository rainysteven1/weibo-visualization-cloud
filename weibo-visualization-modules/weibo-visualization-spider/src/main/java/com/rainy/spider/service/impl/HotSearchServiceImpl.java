package com.rainy.spider.service.impl;

import com.rainy.spider.entity.HotSearch;
import com.rainy.spider.handle.crawler.HotSearchCrawler;
import com.rainy.spider.mapper.HotSearchMapper;
import com.rainy.spider.service.HotSearchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目文件表 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2022-10-13 01:04:44
 */
@Service
public class HotSearchServiceImpl extends ServiceImpl<HotSearchMapper, HotSearch> implements HotSearchService {

    @Autowired
    private HotSearchMapper hotSearchMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public boolean crawler() {
        // TODO 未能修改时间
        HotSearchCrawler hotSearchCrawler = new HotSearchCrawler();
        return saveBatch(hotSearchCrawler.crawler());
    }

}
