package cn.card.domain;

import java.util.ArrayList;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNull() {
            addCriterion("occupation is null");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNotNull() {
            addCriterion("occupation is not null");
            return (Criteria) this;
        }

        public Criteria andOccupationEqualTo(String value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(String value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(String value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(String value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(String value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(String value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLike(String value) {
            addCriterion("occupation like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotLike(String value) {
            addCriterion("occupation not like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<String> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<String> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(String value1, String value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(String value1, String value2) {
            addCriterion("occupation not between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("url is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("url is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("url like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("url not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("url not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkIsNull() {
            addCriterion("phone_work is null");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkIsNotNull() {
            addCriterion("phone_work is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkEqualTo(Long value) {
            addCriterion("phone_work =", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotEqualTo(Long value) {
            addCriterion("phone_work <>", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkGreaterThan(Long value) {
            addCriterion("phone_work >", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_work >=", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkLessThan(Long value) {
            addCriterion("phone_work <", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkLessThanOrEqualTo(Long value) {
            addCriterion("phone_work <=", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkIn(List<Long> values) {
            addCriterion("phone_work in", values, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotIn(List<Long> values) {
            addCriterion("phone_work not in", values, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkBetween(Long value1, Long value2) {
            addCriterion("phone_work between", value1, value2, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotBetween(Long value1, Long value2) {
            addCriterion("phone_work not between", value1, value2, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileIsNull() {
            addCriterion("phone_mobile is null");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileIsNotNull() {
            addCriterion("phone_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileEqualTo(Long value) {
            addCriterion("phone_mobile =", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotEqualTo(Long value) {
            addCriterion("phone_mobile <>", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileGreaterThan(Long value) {
            addCriterion("phone_mobile >", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_mobile >=", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileLessThan(Long value) {
            addCriterion("phone_mobile <", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileLessThanOrEqualTo(Long value) {
            addCriterion("phone_mobile <=", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileIn(List<Long> values) {
            addCriterion("phone_mobile in", values, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotIn(List<Long> values) {
            addCriterion("phone_mobile not in", values, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileBetween(Long value1, Long value2) {
            addCriterion("phone_mobile between", value1, value2, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotBetween(Long value1, Long value2) {
            addCriterion("phone_mobile not between", value1, value2, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeIsNull() {
            addCriterion("phone_home is null");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeIsNotNull() {
            addCriterion("phone_home is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeEqualTo(Long value) {
            addCriterion("phone_home =", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotEqualTo(Long value) {
            addCriterion("phone_home <>", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeGreaterThan(Long value) {
            addCriterion("phone_home >", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_home >=", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeLessThan(Long value) {
            addCriterion("phone_home <", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeLessThanOrEqualTo(Long value) {
            addCriterion("phone_home <=", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeIn(List<Long> values) {
            addCriterion("phone_home in", values, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotIn(List<Long> values) {
            addCriterion("phone_home not in", values, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeBetween(Long value1, Long value2) {
            addCriterion("phone_home between", value1, value2, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotBetween(Long value1, Long value2) {
            addCriterion("phone_home not between", value1, value2, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andAddressWorkIsNull() {
            addCriterion("address_work is null");
            return (Criteria) this;
        }

        public Criteria andAddressWorkIsNotNull() {
            addCriterion("address_work is not null");
            return (Criteria) this;
        }

        public Criteria andAddressWorkEqualTo(String value) {
            addCriterion("address_work =", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotEqualTo(String value) {
            addCriterion("address_work <>", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkGreaterThan(String value) {
            addCriterion("address_work >", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkGreaterThanOrEqualTo(String value) {
            addCriterion("address_work >=", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkLessThan(String value) {
            addCriterion("address_work <", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkLessThanOrEqualTo(String value) {
            addCriterion("address_work <=", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkLike(String value) {
            addCriterion("address_work like", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotLike(String value) {
            addCriterion("address_work not like", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkIn(List<String> values) {
            addCriterion("address_work in", values, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotIn(List<String> values) {
            addCriterion("address_work not in", values, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkBetween(String value1, String value2) {
            addCriterion("address_work between", value1, value2, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotBetween(String value1, String value2) {
            addCriterion("address_work not between", value1, value2, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressHomeIsNull() {
            addCriterion("address_home is null");
            return (Criteria) this;
        }

        public Criteria andAddressHomeIsNotNull() {
            addCriterion("address_home is not null");
            return (Criteria) this;
        }

        public Criteria andAddressHomeEqualTo(String value) {
            addCriterion("address_home =", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotEqualTo(String value) {
            addCriterion("address_home <>", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeGreaterThan(String value) {
            addCriterion("address_home >", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeGreaterThanOrEqualTo(String value) {
            addCriterion("address_home >=", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeLessThan(String value) {
            addCriterion("address_home <", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeLessThanOrEqualTo(String value) {
            addCriterion("address_home <=", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeLike(String value) {
            addCriterion("address_home like", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotLike(String value) {
            addCriterion("address_home not like", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeIn(List<String> values) {
            addCriterion("address_home in", values, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotIn(List<String> values) {
            addCriterion("address_home not in", values, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeBetween(String value1, String value2) {
            addCriterion("address_home between", value1, value2, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotBetween(String value1, String value2) {
            addCriterion("address_home not between", value1, value2, "addressHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeIsNull() {
            addCriterion("fax_home is null");
            return (Criteria) this;
        }

        public Criteria andFaxHomeIsNotNull() {
            addCriterion("fax_home is not null");
            return (Criteria) this;
        }

        public Criteria andFaxHomeEqualTo(Long value) {
            addCriterion("fax_home =", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotEqualTo(Long value) {
            addCriterion("fax_home <>", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeGreaterThan(Long value) {
            addCriterion("fax_home >", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeGreaterThanOrEqualTo(Long value) {
            addCriterion("fax_home >=", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeLessThan(Long value) {
            addCriterion("fax_home <", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeLessThanOrEqualTo(Long value) {
            addCriterion("fax_home <=", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeIn(List<Long> values) {
            addCriterion("fax_home in", values, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotIn(List<Long> values) {
            addCriterion("fax_home not in", values, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeBetween(Long value1, Long value2) {
            addCriterion("fax_home between", value1, value2, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotBetween(Long value1, Long value2) {
            addCriterion("fax_home not between", value1, value2, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxWorkIsNull() {
            addCriterion("fax_work is null");
            return (Criteria) this;
        }

        public Criteria andFaxWorkIsNotNull() {
            addCriterion("fax_work is not null");
            return (Criteria) this;
        }

        public Criteria andFaxWorkEqualTo(Long value) {
            addCriterion("fax_work =", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotEqualTo(Long value) {
            addCriterion("fax_work <>", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkGreaterThan(Long value) {
            addCriterion("fax_work >", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkGreaterThanOrEqualTo(Long value) {
            addCriterion("fax_work >=", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkLessThan(Long value) {
            addCriterion("fax_work <", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkLessThanOrEqualTo(Long value) {
            addCriterion("fax_work <=", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkIn(List<Long> values) {
            addCriterion("fax_work in", values, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotIn(List<Long> values) {
            addCriterion("fax_work not in", values, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkBetween(Long value1, Long value2) {
            addCriterion("fax_work between", value1, value2, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotBetween(Long value1, Long value2) {
            addCriterion("fax_work not between", value1, value2, "faxWork");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("role is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("role is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(Integer value) {
            addCriterion("role =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(Integer value) {
            addCriterion("role <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(Integer value) {
            addCriterion("role >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(Integer value) {
            addCriterion("role >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(Integer value) {
            addCriterion("role <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(Integer value) {
            addCriterion("role <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<Integer> values) {
            addCriterion("role in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<Integer> values) {
            addCriterion("role not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(Integer value1, Integer value2) {
            addCriterion("role between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(Integer value1, Integer value2) {
            addCriterion("role not between", value1, value2, "role");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}