package cn.card.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Card {
    private Integer id;

    private String username;

    private Integer template;

    @JsonIgnore
    private String background;

    @JsonIgnore
    private String logo;

    private Double logoX;

    private Double logoY;

    private Boolean name;

    private Boolean occupation;

    private Boolean email;

    private Integer url;

    private Boolean phoneWork;

    private Boolean phoneMobile;

    private Boolean phoneHome;

    private Boolean addressWork;

    private Boolean addressHome;

    private Boolean faxHome;

    private Boolean faxWork;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getTemplate() {
        return template;
    }

    public void setTemplate(Integer template) {
        this.template = template;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background == null ? null : background.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public Double getLogoX() {
        return logoX;
    }

    public void setLogoX(Double logoX) {
        this.logoX = logoX;
    }

    public Double getLogoY() {
        return logoY;
    }

    public void setLogoY(Double logoY) {
        this.logoY = logoY;
    }

    public Boolean getName() {
        return name;
    }

    public void setName(Boolean name) {
        this.name = name;
    }

    public Boolean getOccupation() {
        return occupation;
    }

    public void setOccupation(Boolean occupation) {
        this.occupation = occupation;
    }

    public Boolean getEmail() {
        return email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public Boolean getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(Boolean phoneWork) {
        this.phoneWork = phoneWork;
    }

    public Boolean getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(Boolean phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public Boolean getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(Boolean phoneHome) {
        this.phoneHome = phoneHome;
    }

    public Boolean getAddressWork() {
        return addressWork;
    }

    public void setAddressWork(Boolean addressWork) {
        this.addressWork = addressWork;
    }

    public Boolean getAddressHome() {
        return addressHome;
    }

    public void setAddressHome(Boolean addressHome) {
        this.addressHome = addressHome;
    }

    public Boolean getFaxHome() {
        return faxHome;
    }

    public void setFaxHome(Boolean faxHome) {
        this.faxHome = faxHome;
    }

    public Boolean getFaxWork() {
        return faxWork;
    }

    public void setFaxWork(Boolean faxWork) {
        this.faxWork = faxWork;
    }
}