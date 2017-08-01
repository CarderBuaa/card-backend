package cn.card.utils.access_token;

import cn.card.exception.TokenException;
import cn.card.exception.baseException.BaseException;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Description:通过AOP的方式检查controller方法是否有token
 * Created by z on 2017/7/27.
 */
public class SecurityAspect {

    private TokenManager tokenManager;

    private static final String tokenName = "Access-Token";

    public void setTokenManager(TokenManager tokenManager) {

        this.tokenManager = tokenManager;

    }

    public Object execute(ProceedingJoinPoint pjp) throws Throwable {

        // 从切点上获取目标方法
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();

        Method method = methodSignature.getMethod();

        //如果方法不用检查token，则放行
        if(method.isAnnotationPresent(IgnoreSecurity.class)){
            return pjp.proceed();

        }

        //获取当前上下文的request的header信息
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) requestAttribute;
        HttpServletRequest request = sra.getRequest();
        String token = request.getHeader(tokenName);

        //如果访问对象的令牌不对，则报错
        if (!tokenManager.checkToken(token)) {
            throw new TokenException();
        }

        return pjp.proceed();

    }
}
