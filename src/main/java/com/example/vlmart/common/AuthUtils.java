package com.example.vlmart.common;

import javax.servlet.http.HttpSession;

public class AuthUtils {
    public static boolean isLogin(HttpSession session) {
        return DataUtils.notNullOrEmpty(session.getAttribute("user"));
    }

    public static String isAuthenticated(HttpSession session) {
        if (!isLogin(session)) {
            return "redirect:/dashboard/login";
        }

        return "";
    }
}
