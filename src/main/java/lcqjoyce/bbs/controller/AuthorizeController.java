package lcqjoyce.bbs.controller;


import lcqjoyce.bbs.dto.AccessTokenDTO;
import lcqjoyce.bbs.dto.GithubUserDTO;
import lcqjoyce.bbs.entity.User;
import lcqjoyce.bbs.provider.GithubProvider;

import lcqjoyce.bbs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author LCQJOYCE
 */
@Controller
@PropertySource({"classpath:application.properties"})
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserService  userService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken=githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("accessToken:"+accessToken);
        GithubUserDTO githubUserDTO = githubProvider. gerUser(accessToken);
        System.out.println(githubUserDTO.toString());
        if(githubUserDTO!=null && githubUserDTO.getId()!=null){
            User user = new User();
            user.setAccountId(String.valueOf(githubUserDTO.getId()));
            user.setName(githubUserDTO.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setBio(githubUserDTO.getBio());
            user.setAvatarUrl(githubUserDTO.getAvatarUrl());
            System.out.println(userService.insert(user));
            response.addCookie(new Cookie("token",token));
            return  "redirect:/";

        }else {
            //登陆失败请重新登陆
            return  "redirect:/";
        }
    }
}

