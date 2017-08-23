package cn.card.domain;

public class User {
    private String username;

    private String password;

    private String name;

    private String occupation;

    private String email;

    private String url;

    private Long phoneWork;

    private Long phoneMobile;

    private Long phoneHome;

    private String addressWork;

    private String addressHome;

    private Long faxHome;

    private Long faxWork;

    private Integer role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getPhoneWork() {
        return phoneWork;
    }

    public void setPhoneWork(Long phoneWork) {
        this.phoneWork = phoneWork;
    }

    public Long getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(Long phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public Long getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(Long phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getAddressWork() {
        return addressWork;
    }

    public void setAddressWork(String addressWork) {
        this.addressWork = addressWork == null ? null : addressWork.trim();
    }

    public String getAddressHome() {
        return addressHome;
    }

    public void setAddressHome(String addressHome) {
        this.addressHome = addressHome == null ? null : addressHome.trim();
    }

    public Long getFaxHome() {
        return faxHome;
    }

    public void setFaxHome(Long faxHome) {
        this.faxHome = faxHome;
    }

    public Long getFaxWork() {
        return faxWork;
    }

    public void setFaxWork(Long faxWork) {
        this.faxWork = faxWork;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}