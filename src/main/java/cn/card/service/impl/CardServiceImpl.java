package cn.card.service.impl;

import cn.card.dao.CardMapper;
import cn.card.domain.Card;

import cn.card.domain.CardExample;
import cn.card.exception.CardNotFoundException;
import cn.card.exception.baseException.BaseException;
import cn.card.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description: card的service类
 * Created by z on 2017/7/31.
 */
@Service("cardService")
public class CardServiceImpl implements CardService{

    private CardMapper cardMapper;

    @Autowired
    public void setCardMapper(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void createRecord(Card card) throws Exception{

        //background,template,usernanme为card的非空项
        if((card.getBackground() == null || card.getBackground().equals("")) ||
                (card.getTemplate() == null) ||
                (card.getUsername() == null || card.getUsername().equals(""))){
            throw new BaseException(HttpStatus.BAD_REQUEST, "card信息非空项为空");
        }
        cardMapper.insertSelective(card);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCardInfo(Card card) throws Exception{
        //id要一定存在
        if(card.getId() == null){
            throw new BaseException(HttpStatus.BAD_REQUEST, "用户名片ID不存在于上传信息中");
        }
        cardMapper.updateByPrimaryKeySelective(card);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Card> findRecordList(Card card) throws Exception{

        //设置查询条件
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(card.getUsername());

        return cardMapper.selectByExample(example);
    }

    //设置card ID主键
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteCard(Card card) throws Exception{
        if(card.getId() == null){
            throw new BaseException(HttpStatus.BAD_REQUEST, "名片ID不能为空");
        }
        cardMapper.deleteByPrimaryKey(card.getId());
    }


    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Card findCardByIDAndUsername(Card card) throws Exception{
        //设置查询条件
        CardExample example = new CardExample();
        CardExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(card.getId());
        criteria.andUsernameEqualTo(card.getUsername());

        List<Card> list = cardMapper.selectByExample(example);
        //list为空可能为空
        if(list == null || list.isEmpty()){
            throw new CardNotFoundException();
        }
        return list.get(0);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<Card> findAllCard() throws Exception {
        CardExample example = new CardExample();
        return cardMapper.selectByExample(example);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Card findCardByID(Card card) throws Exception {
        Card check = cardMapper.selectByPrimaryKey(card.getId());
        //找不到名片
        if(check == null){
            throw new CardNotFoundException();
        }
        return check;
    }


}
