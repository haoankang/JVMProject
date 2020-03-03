package ank.hao.springmvc.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@Order(1)
@WebFilter("/*")
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter init..");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Filter doFilter..");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("Filter afterFilter..");
    }

    @Override
    public void destroy() {
        System.out.println("Filter destory..");
    }
}
