package lcqjoyce.bbs.controller;


import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.mapper.UserMapper;
import lcqjoyce.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    User user = userService.findByToken(value);
                    if (user != null) {
                        System.out.println("已有登录用户"+user.getName());
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }

            }
        } catch (NullPointerException e) {
            System.out.println("未登录");
        }
        return "index";
    }
}
