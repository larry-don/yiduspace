package io.yiduspace.community.controller;

import io.yiduspace.community.dto.AccessTokenDto;
import io.yiduspace.community.dto.GithubUserDto;
import io.yiduspace.community.model.User;
import io.yiduspace.community.provider.GithubProvider;
import io.yiduspace.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthorizeController {

    @Value("${github.client.id}")
    private String client_id;
    @Value("${github.client.secret}")
    private String client_secret;
    @Value("${github.redirect.url}")
    private String redirect_uri;

    @Autowired
    private UserService userService;

    @Autowired
    private GithubProvider githubProvider;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code, HttpServletResponse response){
        AccessTokenDto accessTokenDTO = new AccessTokenDto();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUserDto githubUser = githubProvider.getUser(accessToken);
        if(githubUser != null){
            userService.createOrUpdateUser(githubUser);
            User user = userService.getUserByAccountId(githubUser.getId());
            response.addCookie(new Cookie("token",user.getToken()));
        }
        return "redirect:/";

    }
}
