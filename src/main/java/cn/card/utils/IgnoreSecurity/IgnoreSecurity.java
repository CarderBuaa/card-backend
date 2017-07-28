package cn.card.utils.IgnoreSecurity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:用于标注不需要检查access_token的controller方法
 * Created by z on 2017/7/28.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME )
public @interface IgnoreSecurity {
}
