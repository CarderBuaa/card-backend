package cn.card.domain;

import java.util.ArrayList;
import java.util.List;

public class CardExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andTemplateIsNull() {
            addCriterion("template is null");
            return (Criteria) this;
        }

        public Criteria andTemplateIsNotNull() {
            addCriterion("template is not null");
            return (Criteria) this;
        }

        public Criteria andTemplateEqualTo(Integer value) {
            addCriterion("template =", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotEqualTo(Integer value) {
            addCriterion("template <>", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThan(Integer value) {
            addCriterion("template >", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateGreaterThanOrEqualTo(Integer value) {
            addCriterion("template >=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThan(Integer value) {
            addCriterion("template <", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateLessThanOrEqualTo(Integer value) {
            addCriterion("template <=", value, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateIn(List<Integer> values) {
            addCriterion("template in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotIn(List<Integer> values) {
            addCriterion("template not in", values, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateBetween(Integer value1, Integer value2) {
            addCriterion("template between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andTemplateNotBetween(Integer value1, Integer value2) {
            addCriterion("template not between", value1, value2, "template");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNull() {
            addCriterion("background is null");
            return (Criteria) this;
        }

        public Criteria andBackgroundIsNotNull() {
            addCriterion("background is not null");
            return (Criteria) this;
        }

        public Criteria andBackgroundEqualTo(String value) {
            addCriterion("background =", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotEqualTo(String value) {
            addCriterion("background <>", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThan(String value) {
            addCriterion("background >", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundGreaterThanOrEqualTo(String value) {
            addCriterion("background >=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThan(String value) {
            addCriterion("background <", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLessThanOrEqualTo(String value) {
            addCriterion("background <=", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundLike(String value) {
            addCriterion("background like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotLike(String value) {
            addCriterion("background not like", value, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundIn(List<String> values) {
            addCriterion("background in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotIn(List<String> values) {
            addCriterion("background not in", values, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundBetween(String value1, String value2) {
            addCriterion("background between", value1, value2, "background");
            return (Criteria) this;
        }

        public Criteria andBackgroundNotBetween(String value1, String value2) {
            addCriterion("background not between", value1, value2, "background");
            return (Criteria) this;
        }

        public Criteria andLogoIsNull() {
            addCriterion("logo is null");
            return (Criteria) this;
        }

        public Criteria andLogoIsNotNull() {
            addCriterion("logo is not null");
            return (Criteria) this;
        }

        public Criteria andLogoEqualTo(String value) {
            addCriterion("logo =", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotEqualTo(String value) {
            addCriterion("logo <>", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThan(String value) {
            addCriterion("logo >", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoGreaterThanOrEqualTo(String value) {
            addCriterion("logo >=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThan(String value) {
            addCriterion("logo <", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLessThanOrEqualTo(String value) {
            addCriterion("logo <=", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoLike(String value) {
            addCriterion("logo like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotLike(String value) {
            addCriterion("logo not like", value, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoIn(List<String> values) {
            addCriterion("logo in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotIn(List<String> values) {
            addCriterion("logo not in", values, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoBetween(String value1, String value2) {
            addCriterion("logo between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoNotBetween(String value1, String value2) {
            addCriterion("logo not between", value1, value2, "logo");
            return (Criteria) this;
        }

        public Criteria andLogoXIsNull() {
            addCriterion("logo_x is null");
            return (Criteria) this;
        }

        public Criteria andLogoXIsNotNull() {
            addCriterion("logo_x is not null");
            return (Criteria) this;
        }

        public Criteria andLogoXEqualTo(Double value) {
            addCriterion("logo_x =", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXNotEqualTo(Double value) {
            addCriterion("logo_x <>", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXGreaterThan(Double value) {
            addCriterion("logo_x >", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXGreaterThanOrEqualTo(Double value) {
            addCriterion("logo_x >=", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXLessThan(Double value) {
            addCriterion("logo_x <", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXLessThanOrEqualTo(Double value) {
            addCriterion("logo_x <=", value, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXIn(List<Double> values) {
            addCriterion("logo_x in", values, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXNotIn(List<Double> values) {
            addCriterion("logo_x not in", values, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXBetween(Double value1, Double value2) {
            addCriterion("logo_x between", value1, value2, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoXNotBetween(Double value1, Double value2) {
            addCriterion("logo_x not between", value1, value2, "logoX");
            return (Criteria) this;
        }

        public Criteria andLogoYIsNull() {
            addCriterion("logo_y is null");
            return (Criteria) this;
        }

        public Criteria andLogoYIsNotNull() {
            addCriterion("logo_y is not null");
            return (Criteria) this;
        }

        public Criteria andLogoYEqualTo(Double value) {
            addCriterion("logo_y =", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYNotEqualTo(Double value) {
            addCriterion("logo_y <>", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYGreaterThan(Double value) {
            addCriterion("logo_y >", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYGreaterThanOrEqualTo(Double value) {
            addCriterion("logo_y >=", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYLessThan(Double value) {
            addCriterion("logo_y <", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYLessThanOrEqualTo(Double value) {
            addCriterion("logo_y <=", value, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYIn(List<Double> values) {
            addCriterion("logo_y in", values, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYNotIn(List<Double> values) {
            addCriterion("logo_y not in", values, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYBetween(Double value1, Double value2) {
            addCriterion("logo_y between", value1, value2, "logoY");
            return (Criteria) this;
        }

        public Criteria andLogoYNotBetween(Double value1, Double value2) {
            addCriterion("logo_y not between", value1, value2, "logoY");
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

        public Criteria andNameEqualTo(Boolean value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(Boolean value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(Boolean value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(Boolean value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(Boolean value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(Boolean value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<Boolean> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<Boolean> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(Boolean value1, Boolean value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andOccupationEqualTo(Boolean value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(Boolean value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(Boolean value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(Boolean value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(Boolean value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(Boolean value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<Boolean> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<Boolean> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(Boolean value1, Boolean value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andEmailEqualTo(Boolean value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(Boolean value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(Boolean value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(Boolean value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(Boolean value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(Boolean value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<Boolean> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<Boolean> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(Boolean value1, Boolean value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andUrlEqualTo(Integer value) {
            addCriterion("url =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(Integer value) {
            addCriterion("url <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(Integer value) {
            addCriterion("url >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(Integer value) {
            addCriterion("url >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(Integer value) {
            addCriterion("url <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(Integer value) {
            addCriterion("url <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<Integer> values) {
            addCriterion("url in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<Integer> values) {
            addCriterion("url not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(Integer value1, Integer value2) {
            addCriterion("url between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(Integer value1, Integer value2) {
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

        public Criteria andPhoneWorkEqualTo(Boolean value) {
            addCriterion("phone_work =", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotEqualTo(Boolean value) {
            addCriterion("phone_work <>", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkGreaterThan(Boolean value) {
            addCriterion("phone_work >", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("phone_work >=", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkLessThan(Boolean value) {
            addCriterion("phone_work <", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkLessThanOrEqualTo(Boolean value) {
            addCriterion("phone_work <=", value, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkIn(List<Boolean> values) {
            addCriterion("phone_work in", values, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotIn(List<Boolean> values) {
            addCriterion("phone_work not in", values, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkBetween(Boolean value1, Boolean value2) {
            addCriterion("phone_work between", value1, value2, "phoneWork");
            return (Criteria) this;
        }

        public Criteria andPhoneWorkNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andPhoneMobileEqualTo(Boolean value) {
            addCriterion("phone_mobile =", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotEqualTo(Boolean value) {
            addCriterion("phone_mobile <>", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileGreaterThan(Boolean value) {
            addCriterion("phone_mobile >", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileGreaterThanOrEqualTo(Boolean value) {
            addCriterion("phone_mobile >=", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileLessThan(Boolean value) {
            addCriterion("phone_mobile <", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileLessThanOrEqualTo(Boolean value) {
            addCriterion("phone_mobile <=", value, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileIn(List<Boolean> values) {
            addCriterion("phone_mobile in", values, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotIn(List<Boolean> values) {
            addCriterion("phone_mobile not in", values, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileBetween(Boolean value1, Boolean value2) {
            addCriterion("phone_mobile between", value1, value2, "phoneMobile");
            return (Criteria) this;
        }

        public Criteria andPhoneMobileNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andPhoneHomeEqualTo(Boolean value) {
            addCriterion("phone_home =", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotEqualTo(Boolean value) {
            addCriterion("phone_home <>", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeGreaterThan(Boolean value) {
            addCriterion("phone_home >", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("phone_home >=", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeLessThan(Boolean value) {
            addCriterion("phone_home <", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeLessThanOrEqualTo(Boolean value) {
            addCriterion("phone_home <=", value, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeIn(List<Boolean> values) {
            addCriterion("phone_home in", values, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotIn(List<Boolean> values) {
            addCriterion("phone_home not in", values, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeBetween(Boolean value1, Boolean value2) {
            addCriterion("phone_home between", value1, value2, "phoneHome");
            return (Criteria) this;
        }

        public Criteria andPhoneHomeNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andAddressWorkEqualTo(Boolean value) {
            addCriterion("address_work =", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotEqualTo(Boolean value) {
            addCriterion("address_work <>", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkGreaterThan(Boolean value) {
            addCriterion("address_work >", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("address_work >=", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkLessThan(Boolean value) {
            addCriterion("address_work <", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkLessThanOrEqualTo(Boolean value) {
            addCriterion("address_work <=", value, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkIn(List<Boolean> values) {
            addCriterion("address_work in", values, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotIn(List<Boolean> values) {
            addCriterion("address_work not in", values, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkBetween(Boolean value1, Boolean value2) {
            addCriterion("address_work between", value1, value2, "addressWork");
            return (Criteria) this;
        }

        public Criteria andAddressWorkNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andAddressHomeEqualTo(Boolean value) {
            addCriterion("address_home =", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotEqualTo(Boolean value) {
            addCriterion("address_home <>", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeGreaterThan(Boolean value) {
            addCriterion("address_home >", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("address_home >=", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeLessThan(Boolean value) {
            addCriterion("address_home <", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeLessThanOrEqualTo(Boolean value) {
            addCriterion("address_home <=", value, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeIn(List<Boolean> values) {
            addCriterion("address_home in", values, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotIn(List<Boolean> values) {
            addCriterion("address_home not in", values, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeBetween(Boolean value1, Boolean value2) {
            addCriterion("address_home between", value1, value2, "addressHome");
            return (Criteria) this;
        }

        public Criteria andAddressHomeNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andFaxHomeEqualTo(Boolean value) {
            addCriterion("fax_home =", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotEqualTo(Boolean value) {
            addCriterion("fax_home <>", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeGreaterThan(Boolean value) {
            addCriterion("fax_home >", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fax_home >=", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeLessThan(Boolean value) {
            addCriterion("fax_home <", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeLessThanOrEqualTo(Boolean value) {
            addCriterion("fax_home <=", value, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeIn(List<Boolean> values) {
            addCriterion("fax_home in", values, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotIn(List<Boolean> values) {
            addCriterion("fax_home not in", values, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeBetween(Boolean value1, Boolean value2) {
            addCriterion("fax_home between", value1, value2, "faxHome");
            return (Criteria) this;
        }

        public Criteria andFaxHomeNotBetween(Boolean value1, Boolean value2) {
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

        public Criteria andFaxWorkEqualTo(Boolean value) {
            addCriterion("fax_work =", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotEqualTo(Boolean value) {
            addCriterion("fax_work <>", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkGreaterThan(Boolean value) {
            addCriterion("fax_work >", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkGreaterThanOrEqualTo(Boolean value) {
            addCriterion("fax_work >=", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkLessThan(Boolean value) {
            addCriterion("fax_work <", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkLessThanOrEqualTo(Boolean value) {
            addCriterion("fax_work <=", value, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkIn(List<Boolean> values) {
            addCriterion("fax_work in", values, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotIn(List<Boolean> values) {
            addCriterion("fax_work not in", values, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkBetween(Boolean value1, Boolean value2) {
            addCriterion("fax_work between", value1, value2, "faxWork");
            return (Criteria) this;
        }

        public Criteria andFaxWorkNotBetween(Boolean value1, Boolean value2) {
            addCriterion("fax_work not between", value1, value2, "faxWork");
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