package pers.jason.example.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author 姜治昊
 * @Description
 * @Date 2019/11/4 9:31
 */
@WebFilter(urlPatterns = "/*", filterName = "uriFilter")
public class UriFilter implements Filter {

  private Logger logger = LoggerFactory.getLogger(UriFilter.class);

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    logger.debug("uriFilter init ...");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String uri = ((HttpServletRequest) request).getRequestURI();
    logger.info("request: " + uri);
  }

  @Override
  public void destroy() {

  }

}
