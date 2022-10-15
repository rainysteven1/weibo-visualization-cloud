package com.rainy.spider.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.rainy.common.entity.BaseEntity;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目文件表
 * </p>
 *
 * @author rainy
 * @since 2022-10-13 01:04:44
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("hot_search")
@ApiModel(value = "HotSearch对象", description = "项目文件表")
@Builder
public class HotSearch extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("热搜ID")
    @TableId(value = "hot_search_id", type = IdType.AUTO)
    private Long hotSearchId;

    @ApiModelProperty("热搜排名")
    @TableField("hot_search_rank")
    private Integer hotSearchRank;

    @ApiModelProperty("热搜标题")
    @TableField("hot_search_title")
    private String hotSearchTitle;

    @ApiModelProperty("热搜热度")
    @TableField("hot_search_heat")
    private String hotSearchHeat;

    @ApiModelProperty("热搜URL")
    @TableField("hot_search_url")
    private String hotSearchUrl;


}
