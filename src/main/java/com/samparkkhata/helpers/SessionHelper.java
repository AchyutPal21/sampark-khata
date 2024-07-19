package com.samparkkhata.helpers;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

/**
 * This class SessionHelper is to remove the session alert message when page is
 * refreshed
 */
@Component
public class SessionHelper {

    public static void removeAlertMessage() {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();

            if (requestAttributes == null) {
                return;
            }

            HttpSession httpSession = ((ServletRequestAttributes) requestAttributes).getRequest().getSession();
            httpSession.removeAttribute("alertMessages");
        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Error in SessionHelper: " + exception.getMessage());
        }
    }
}
