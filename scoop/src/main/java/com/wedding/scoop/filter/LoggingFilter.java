package com.wedding.scoop.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@WebFilter(urlPatterns = "/*")
@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest httpRequest) {
            String clientIp = request.getRemoteAddr();
            int clientPort = request.getRemotePort();
            String requestUrl = httpRequest.getRequestURL().toString();

            // Convert IPv6 to IPv4 if necessary
            clientIp = convertIPv6ToIPv4(clientIp);

            log.info("Incoming Request: IP = {}, Port = {}, URL = {}", clientIp, clientPort, requestUrl);
        }

        chain.doFilter(request, response);
    }

    private String convertIPv6ToIPv4(String ip) {
        if (ip != null && ip.contains("::")) {
            try {
                InetAddress inetAddress = InetAddress.getByName(ip);
                if (inetAddress.getAddress().length == 4) {
                    return inetAddress.getHostAddress();
                }
            } catch (UnknownHostException e) {
                log.error("Error converting IP: {}", ip, e);
            }
        }
        return ip;
    }
}
