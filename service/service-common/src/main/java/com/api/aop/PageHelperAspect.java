package com.api.aop;

import com.api.util.BaseApiService;
import com.api.util.Notice;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Component
@Aspect
@Slf4j
public class PageHelperAspect extends BaseApiService {
    @Pointcut("@annotation(com.api.aop.ExtPageHelper)")
    public void annotationPointcut() {
    }

    @Around("annotationPointcut()")
    public Object serviceImplAop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (getPageSize() == null || getPageNum() == null) {
            return new Notice(HttpStatus.INTERNAL_SERVER_ERROR, "错误");
        }
        int pageSize = Integer.parseInt(getPageSize());
        int pageNum = Integer.parseInt(getPageNum());
        Page page = PageHelper.startPage(pageNum, pageSize);
        Object object = proceedingJoinPoint.proceed();
        if (object instanceof Notice) {
            Notice notice = (Notice) object;
            if (notice.getState() == 200) {
                notice.setTotal(page.getTotal());
                notice.setPageNum(page.getPageNum());
                notice.setPages(page.getPages());
                return notice;
            }
        }
        return object;
    }
}
