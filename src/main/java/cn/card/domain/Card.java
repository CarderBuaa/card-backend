package cn.card.domain;

/**
 * Description:card的pojo类，用来保存生成card的信息
 * Created by z on 2017/7/31.
 */
public class Card {

    //主键自增ID
    private int id;
    //外键于user表的username
    private String username;
    //用于存储背景图片的文件名
    private String background;
    //
    private String name;
    //以下信息基本同user

    private String occupation_list;
    private String email_list;
    private String phone_list;
    private String address_list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation_list() {
        return occupation_list;
    }

    public void setOccupation_list(String occupation_list) {
        this.occupation_list = occupation_list;
    }

    public String getEmail_list() {
        return email_list;
    }

    public void setEmail_list(String email_list) {
        this.email_list = email_list;
    }

    public String getPhone_list() {
        return phone_list;
    }

    public void setPhone_list(String phone_list) {
        this.phone_list = phone_list;
    }

    public String getAddress_list() {
        return address_list;
    }

    public void setAddress_list(String address_list) {
        this.address_list = address_list;
    }

    @Override
    public String toString() {
        return "Card{" +
                "username='" + username + '\'' +
                ", background='" + background + '\'' +
                ", name='" + name + '\'' +
                ", occupation_list='" + occupation_list + '\'' +
                ", email_list='" + email_list + '\'' +
                ", phone_list='" + phone_list + '\'' +
                ", address_list='" + address_list + '\'' +
                '}';
    }
}

