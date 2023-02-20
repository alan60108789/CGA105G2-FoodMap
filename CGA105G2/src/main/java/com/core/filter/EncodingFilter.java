package com.core.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class EncodingFilter implements javax.servlet.Filter {
    // init, destroy, etc. implementation

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException
    {
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response); // pass request to next filter
    }
}
