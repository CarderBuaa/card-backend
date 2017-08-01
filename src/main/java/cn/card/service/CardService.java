package cn.card.service;

import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;

import java.util.List;

/**
 * Description:
 * Created by z on 2017/7/31.
 */
public interface CardService {

    //用于上传文件时向数据库中新建一个上传记录
    void createRecord(CardQueryVo cardQueryVo);

    //用于在上传图片后向其中更新用户信息
    void updateCardInfo(CardQueryVo cardQueryVo);

    //用于寻找一个用户的所有提交记录
    List<CardCustom> findRecordList(CardQueryVo cardQueryVo);

    //删除一个名片
    void deleteCard(CardQueryVo cardQueryVo);

    //通过ID查找一个名片
    CardCustom findCardByIDAndUsername(CardQueryVo cardQueryVo);
}
