package cn.lionl.configuration;

import cn.lionl.annotation.PassToken;
import cn.lionl.dao.User;
import cn.lionl.service.UserRepository;
import cn.lionl.utils.JwtUtil;
import com.auth0.jwt.JWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Configuration
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    UserRepository userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // get token from request
        String token = request.getHeader("token");

        // 1、如果不是映射到方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 2、检查是否有 PassToken注释，有则跳过
        if (method.isAnnotationPresent(PassToken.class)) {
            return true;
        }

        // 3、检查 token
        if (token == null || "".equals(token)) {
            throw new RuntimeException("token 无效");
        }

        // 4、检验 token
        if (!JwtUtil.verify(token)) {
            throw new RuntimeException("token 验证失败");
        }

        // 5、验证用户是否存在
        // 获取 token中的 userId
        long userId = JWT.decode(token).getClaim("userId").asLong();

        User user = userService.findById(userId);
        if (user == null) {
            throw new RuntimeException("用户不                                                                                                                                                                                         存在");
        }

        return true;
    }
}
