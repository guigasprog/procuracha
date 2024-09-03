package br.edu.fema.procuracha.infrastructure.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest requestHttpServelt = (HttpServletRequest) request;
        HttpServletResponse responseHttpServelt = (HttpServletResponse) response;
        String originPermitida = "*";
        responseHttpServelt.setHeader("Access-Control-Allow-Origin", originPermitida);
        responseHttpServelt.setHeader("Access-Control-Allow-Credentials", "true");
        responseHttpServelt.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");


        filterChain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig arg0){

    }
}