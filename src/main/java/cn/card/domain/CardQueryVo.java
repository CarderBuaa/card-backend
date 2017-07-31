package cn.card.domain;

/**
 * 
 * Description: 用于controller向后端传入查询参数的类
 * @author z
 * @date 2017年7月25日
 */
public class CardQueryVo {
	
	private CardCustom cardCustom;

	public CardCustom getCardCustom() {
		return cardCustom;
	}

	public void setCardCustom(CardCustom cardCustom) {
		this.cardCustom = cardCustom;
	}

	@Override
	public String toString() {
		return "CardQueryVo{" +
				"cardCustom=" + cardCustom +
				'}';
	}
}
