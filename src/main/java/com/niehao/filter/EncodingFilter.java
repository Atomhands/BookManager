package com.niehao.filter;

import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {

    private final String name = "encode";
    String encode = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encode = filterConfig.getInitParameter("encode");
        //encode = filterConfig.getInitParameter(name);// 获取 参数
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // System.out.println("设置编码");
        if (encode != null){
            request.setCharacterEncoding(encode);
            response.setContentType("text/html;charset="+encode);
            chain.doFilter(request, response);
        }

        //request.setCharacterEncoding(encode);

    }

    @Override
    public void destroy() {
        // NO
        encode = null;
    }

}
