package cn.card.utils.access_token;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Created by z on 2017/7/27.
 */
public class SecurityAspect {

    private TokenManager tokenManager;

    private static String tokenName = "Access-Token";

    public void setTokenManager(TokenManager tokenManager) {

        this.tokenManager = tokenManager;

    }
    public Object execute(ProceedingJoinPoint pjp) throws Throwable {

        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        Method method = methodSignature.getMethod();

        return null;
    }
}
