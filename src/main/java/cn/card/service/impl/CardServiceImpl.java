package cn.card.service.impl;

import cn.card.dao.CardMapper;
import cn.card.domain.CardCustom;
import cn.card.domain.CardQueryVo;
import cn.card.service.CardService;
import cn.card.utils.TransferData.Transfer;
import cn.card.utils.TransferData.TransferCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Description:
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
    public void createRecord(CardQueryVo cardQueryVo) {
        TransferCard.transferToString(cardQueryVo.getCardCustom());
        cardMapper.createRecord(cardQueryVo);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateCardInfo(CardQueryVo cardQueryVo) {
        TransferCard.transferToString(cardQueryVo.getCardCustom());
        cardMapper.updateCardInfo(cardQueryVo);
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<CardCustom> findRecordList(CardQueryVo cardQueryVo) {
        List<CardCustom> list = cardMapper.findRecordList(cardQueryVo);
        for (CardCustom cardCustom : list){
            TransferCard.transferToList(cardCustom);
        }
        return list;
    }
}
