package com.xw.customize.filter;

//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.AccessControlFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//
//@Component
//public class AdminFilter extends AccessControlFilter {
//
//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
//        if (isLoginRequest(request, response)) {
//            return true;
//        } else {
//            Subject subject = getSubject(request, response);
//            // If principal is not null, then the user is known and should be allowed access.
//            return subject.hasRole("admin");
//        }
//    }
//
//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        saveRequestAndRedirectToLogin(request, response);
//        return false;
//    }
//
//}
