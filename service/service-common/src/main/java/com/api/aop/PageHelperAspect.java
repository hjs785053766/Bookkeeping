package com.api.aop;

import com.api.util.Notice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Component
@Aspect
@Slf4j
public class PageHelperAspect {
    @Pointcut("@annotation(com.api.aop.ExtPageHelper)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object serviceImplAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int pageNum = Integer.parseInt(request.getParameter("pageNum"));
        Page page = PageHelper.startPage(pageNum, pageSize);
        Object object = proceedingJoinPoint.proceed();
        if (object instanceof Notice) {
            Notice notice = (Notice) object;
            if (notice.getState().value() == 200) {
                notice.setTotal(page.getTotal());
                notice.setPageNum(page.getPageNum());
                return notice;
            }
        }
        return object;
    }
}
