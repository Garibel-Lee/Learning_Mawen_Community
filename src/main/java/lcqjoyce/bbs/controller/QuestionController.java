package lcqjoyce.bbs.controller;

import lcqjoyce.bbs.dto.QuestionDTO;
import lcqjoyce.bbs.entity.Question;
import lcqjoyce.bbs.exception.CustomizeErrorCode;
import lcqjoyce.bbs.exception.CustomizeException;
import lcqjoyce.bbs.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.selectByPrimaryKey(id);
        questionService.inView(id);
        model.addAttribute("question",questionDTO);

        return "question";
    }
}
