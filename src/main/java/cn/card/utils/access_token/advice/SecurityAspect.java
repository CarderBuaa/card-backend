package cn.card.utils.access_token.advice;

import cn.card.domain.User;
import cn.card.exception.TokenException;
import cn.card.exception.UserNotFoundException;
import cn.card.exception.baseException.BaseException;
import cn.card.service.UserService;
import cn.card.utils.IgnoreSecurity.IgnoreSecurity;
import cn.card.utils.access_token.TokenManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Description:通过AOP的方式检查controller,同时实现基于资源的用户权限管理
 * Created by z on 2017/7/27.
 */
public class SecurityAspect {

    private TokenManager tokenManager;
    private UserService userService;

    private static final String tokenName = "Access-Token";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

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
        if (token == null || !tokenManager.checkToken(token)) {
            throw new TokenException();
        }

        //获取用户username
        String usernameToken = tokenManager.getUsername(token);
        User userFind = new User();
        userFind.setUsername(usernameToken);
        User user = userService.findUserByUserName(userFind);

        //实现基于角色的权限管理
        //用户不存在
        if(user == null){
          throw new UserNotFoundException();
        }
        String URI = request.getRequestURI();
        //URI匹配 /manage/* 检查管理员角色
        if(URI.contains("/manage/")){
            if(user.getRole() == null || user.getRole() != 1){
                throw new BaseException(HttpStatus.UNAUTHORIZED, "当前用户权限不足");
            }
            return pjp.proceed();
        }
        //URI匹配 /user/{username}
        if(URI.contains("/user/")){
            //管理员可以更改和获取用户信息
            if(user.getRole() != 1) {
                String usernameURI = URI.substring(URI.lastIndexOf('/'));
                if (!usernameURI.equals("/" + usernameToken)) {
                    throw new BaseException(HttpStatus.UNAUTHORIZED, "当前用户权限不足");
                }
            }
            return pjp.proceed();
        }
        return pjp.proceed();
    }
}
