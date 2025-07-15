//package com.hosiky.common.interceptors;
//
//
//import com.github.xiaoymin.knife4j.core.util.StrUtil;
//import com.hosiky.common.constant.JwtClaimsConstant;
//import com.hosiky.common.utils.UserContext;
//
//import io.jsonwebtoken.Claims;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.HandlerMethod;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//
//@Slf4j
//@Component
//public class LoginCheckInterceptor implements HandlerInterceptor {
//
//    @Autowired
//    private JwtProperties jwtProperties;
//
//    @Autowired
//    private JwtUtils jwtUtils;
//
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
////在 Spring MVC 中，Object handler 是在拦截器（Interceptor）中用来表示当前请求将要访问的处理器（Handler）对象
////        判断当前拦截到的是Controller的方法还是其他的资源
//
//        if(!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
////        从请求头中获取令牌
//        String token = request.getHeader("token");
//
//            if(StrUtil.isBlank(token)) {
//            throw new RuntimeException(JwtClaimsConstant.NO_TOKEN);
//        }
//
////        校验jwt令牌
//        try {
//            log.info("校验jwt{}",token);
//            Claims claims = jwtUtils.parseJWT(token);
//            log.info("claims里面的内容：{}",claims);
//
//            boolean flag = claims.containsKey(JwtClaimsConstant.USER_ID);
//            boolean flag1 = claims.containsKey(JwtClaimsConstant.CARUSER_ID);
//            if(flag) {
//                log.info("flag的值是：{}",flag);
//                Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
//
//                log.info("当前用户id: {}",userId);
//                UserContext.setCurrentId(userId);
//            } else if (flag1) {
//                Long carUserId = Long.valueOf(claims.get(JwtClaimsConstant.CARUSER_ID).toString());
//                UserContext.setCurrentId(carUserId);
//            }
//
////            通过，方向
//            return true;
//        } catch (Exception e){
//            e.printStackTrace();
//            response.setStatus(401);
//            return false;
//        }
//    }
//
//
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        // 清理用户
//        UserContext.removeCurrentId();
//    }
//}
