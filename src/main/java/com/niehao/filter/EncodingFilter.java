package com.niehao.filter;

import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {

    private final String name = "encode";
    private String encode;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter(name);// 获取 参数
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // System.out.println("设置编码");
        request.setCharacterEncoding(encode);
        // response.setCharacterEncoding(encode);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // NO
    }

}
