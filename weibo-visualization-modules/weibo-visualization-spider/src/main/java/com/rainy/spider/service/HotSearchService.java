package com.rainy.spider.service;

import com.rainy.spider.entity.HotSearch;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 项目文件表 服务类
 * </p>
 *
 * @author rainy
 * @since 2022-10-13 01:04:44
 */
public interface HotSearchService extends IService<HotSearch> {
    boolean crawler();
}
