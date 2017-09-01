package cn.card.domain;

import java.util.List;

/**
 * Description:
 * Created by z on 2017/8/20.
 */
public class UserCustom extends User{

    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public UserCustom(){}

    public UserCustom(User user) {
        this.setUsername(user.getUsername());

        this.setPassword(user.getPassword());

        this.setName(user.getName());

        this.setOccupation(user.getOccupation());

        this.setEmail(user.getEmail());

        this.setUrl(user.getUrl());

        this.setPhoneWork(user.getPhoneWork());

        this.setPhoneMobile(user.getPhoneMobile());

        this.setPhoneHome(user.getPhoneHome());

        this.setAddressWork(user.getAddressWork());

        this.setAddressHome(user.getAddressHome());

        this.setFaxHome(user.getFaxHome());

        this.setFaxWork(user.getFaxWork());

        this.setRole(user.getRole());
    }

    @Override
    public String toString() {
        return super.toString() +
                "UserCustom{" +
                "cards=" + cards +
                '}';
    }
}
