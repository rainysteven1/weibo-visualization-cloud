SET NAMES utf8mb4;
-- ----------------------------
-- 1、项目文件表
-- ----------------------------
drop table if exists hot_search;

create table hot_search
(
    hot_search_id    bigint(20)   not null auto_increment comment '热搜ID',
    hot_search_rank  int(6)       not null default 0 comment '热搜排名',
    hot_search_title varchar(40)  not null default '' comment '热搜标题',
    hot_search_heat  varchar(15)  not null default '0' comment '热搜热度',
    hot_search_url   varchar(300) not null default '' comment '热搜URL',
    is_delete        tinyint(1)   not null default 1 comment '文件是否删除（0否 1是）',
    create_time      datetime comment '热搜创建时间',
    update_time      datetime comment '热搜更新时间',
    primary key (hot_search_id)
) engine = innodb comment = '项目文件表';
