package lcqjoyce.bbs.controller;

import lcqjoyce.bbs.dto.PageinfoDTO;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.service.NotificationService;
import lcqjoyce.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          Model model,
                          @RequestParam(name = "page", defaultValue = "1") Integer page,
                          @RequestParam(name = "size", defaultValue = "2") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

       if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我的提问");
       /*     PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
            model.addAttribute("pagination", paginationDTO);
        } else if ("replies".equals(action)) {
            PaginationDTO paginationDTO = notificationService.list(user.getId(), page, size);
            model.addAttribute("section", "replies");
            model.addAttribute("pagination", paginationDTO);*/
            model.addAttribute("sectionName", "最新回复");
        }else if ("replies".equals(action) ){
           model.addAttribute("section", "replies");
           model.addAttribute("sectionName", "我的回复");
       }
        PageinfoDTO pageinfoDTO = questionService.listMyQuestion(user.getId(), page, size);
        model.addAttribute("pagination", pageinfoDTO);
        return "profile";
    }
}
