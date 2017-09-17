package cn.card.service;


import cn.card.domain.Card;

import java.util.List;

/**
 * Description:
 * Created by z on 2017/7/31.
 */
public interface CardService {

    //用于上传文件时向数据库中新建一个上传记录
    void createRecord(Card card) throws Exception;

    //用于在上传图片后向其中更新用户信息
    void updateCardInfo(Card card) throws Exception;

    //用于寻找一个用户的所有提交记录
    List<Card> findRecordList(Card card) throws Exception;

    //删除一个名片
    void deleteCard(Card card) throws Exception;

    //通过ID查找一个名片
    Card findCardByIDAndUsername(Card card) throws Exception;

    //返回所有的名片信息
    List<Card> findAllCard() throws Exception;

    Card findCardByID(Card card) throws Exception;
}
