package cn.card.dao;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;

/**
 * Description:用于card的新建和XXX
 * Created by z on 2017/7/31.
 */
public interface CardMapper {
    //用于上传文件时向数据库中新建一个上传记录
    void createRecord(CardQueryVo cardQueryVo);
}
