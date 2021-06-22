package io.yiduspace.community.controller;

import io.yiduspace.community.cache.TagCache;
import io.yiduspace.community.dto.QuestionDTO;
import io.yiduspace.community.model.Question;
import io.yiduspace.community.model.User;
import io.yiduspace.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    //从导航栏提问进入
    @GetMapping("/publish")
    public String publish(Model model) {
        //将tag返回到前端
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    //从编辑处进入，携带问题id
    @GetMapping("/publish/{id}")
    public String publish(@PathVariable("id") Long id, Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        model.addAttribute("title",questionDTO.getTitle());
        model.addAttribute("description",questionDTO.getDescription());
        model.addAttribute("tag",questionDTO.getTag());
        model.addAttribute("id",id);
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    //提交发布问题表单
    @PostMapping("/publish")
    public String publish(@RequestParam(value = "title", required = false) String title,
                          @RequestParam(value = "description", required = false) String description,
                          @RequestParam(value = "tag", required = false) String tag,
                          @RequestParam(value = "id",required = false) Long id,
                          Model model, HttpServletRequest request) {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());
        if(StringUtils.isBlank(title)){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(StringUtils.isBlank(description)){
            model.addAttribute("error","描述信息不能为空");
            return "publish";
        }
        if(StringUtils.isBlank(tag)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        //验证输入标签是否合法
        if(StringUtils.isNotBlank(TagCache.isValid(tag))){
            model.addAttribute("error","输入非法标签:" + TagCache.isValid(tag));
            return "publish";
        }
        User user = (User)request.getSession().getAttribute("user");

        if(user == null){
            model.addAttribute("error","请登录后再发布问题");
            return "publish";
        }

        Question question = new io.yiduspace.community.model.Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setViewCount(0);
        question.setLikeCount(0);
        question.setCommentCount(0);
        question.setId(id);
        questionService.createOrUpdateQuestion(question);
        return "redirect:/";
    }
}
