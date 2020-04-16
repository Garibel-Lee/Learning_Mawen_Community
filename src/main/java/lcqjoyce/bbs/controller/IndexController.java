package lcqjoyce.bbs.controller;


import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.mapper.UserMapper;
import lcqjoyce.bbs.service.QuestionService;
import lcqjoyce.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;


    @Autowired
    private QuestionService questionService;


    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "2") Integer size

    ) {
        try {
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length != 0) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("token")) {
                        String value = cookie.getValue();
                        User user = userService.findByToken(value);
                        if (user != null) {
                            System.out.println("已有登录用户" + user.getName());
                            request.getSession().setAttribute("user", user);
                        }
                        break;
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("未登录");
        }

       PageinfoDTO pagination = questionService.getAll(page,size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
