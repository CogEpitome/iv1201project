package kthknugarna.iv1201project.model;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import kthknugarna.iv1201project.controller.LoginController;

/**
 *
 * @author Anton
 * @author Jonas
 * @author Benjamin
 * 
 * Responsible for controlling which pages are accessible to the current user, and where to redirect them.
 * 
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {
    @EJB
    private LoginController con;

    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Checks an HTTP request made by the user to see if it is allowed, based on the user's authentication status.
     * Also responsible for redirecting the user to the appropriate page.
     * 
     * @param request   The request made by the user.
     * @param response  The response from the server.
     * @param chain     The filterchain responsible for filtering the response.
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();

            if(ses == null){
                if(reqURI.contains("/login.xhtml")) chain.doFilter(request,response);
            }
            if ((reqURI.contains("/login.xhtml") && ses.getAttribute("username") == null)
                || (reqURI.contains("/register.xhtml") && ses.getAttribute("username") == null)
                || (reqURI.contains("/application.xhtml"))
                || (reqURI.contains("/error.xhtml"))
                || (ses.getAttribute("username") != null && reqURI.contains("/applicants.xhtml") && con.getRoleName((String)ses.getAttribute("username")).equals("applicant"))
                || (ses.getAttribute("username") != null && reqURI.contains("/recruiters.xhtml") && con.getRoleName((String)ses.getAttribute("username")).equals("recruiter"))
                || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                if(ses.getAttribute("username") == null)
                    resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
                else if(ses.getAttribute("username") != null && con.getRoleName((String)ses.getAttribute("username")).equals("applicant"))
                    resp.sendRedirect(reqt.getContextPath() + "/faces/applicants.xhtml");
                else if(ses.getAttribute("username") != null && con.getRoleName((String)ses.getAttribute("username")).equals("recruiter"))
                    resp.sendRedirect(reqt.getContextPath() + "/faces/recruiters.xhtml");
            } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}

