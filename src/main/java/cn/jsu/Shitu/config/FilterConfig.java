package cn.jsu.projectmanage.config;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Commit;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xiaxia
 * @title: GlobalCorsConfig
 * @projectName StudentManage
 * @description: TODO
 * @Version 1.0
 * @date 2020/12/315:38
 */
// 设置跨
@Commit
@Order(-9999)
@WebFilter(filterName = "corsFilter", urlPatterns = { "/*" })
@Component
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String origin = httpServletRequest.getHeader(HttpHeaders.ORIGIN);
        if (origin != null)
        {
            if (!origin.equals(httpServletResponse.getHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)))
            {
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, origin);
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma");
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS,"Origin, token, Content-Length, X-Requested-With , yourHeaderFeild, Content-Type, Accept, Authorization");
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, OPTIONS, DELETE");
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                httpServletResponse.addHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
            }
        }
        if("OPTIONS".equals(httpServletRequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request,response);
    }

    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
