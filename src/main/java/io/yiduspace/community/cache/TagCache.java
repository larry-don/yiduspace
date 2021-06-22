package io.yiduspace.community.cache;

import io.yiduspace.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    //将标签放在内存中，此方法用于获取标签
    public static List<TagDTO> get(){
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO program = new TagDTO();
        program.setCategory("开发语言");
        program.setTags(Arrays.asList("javascript", "php", "css", "html", "html5", "java", "node.js", "python", "c++", "c", "golang", "objective-c", "typescript", "shell", "swift", "c#", "sass", "ruby", "bash", "less", "asp.net", "lua", "scala", "coffeescript", "actionscript", "rust", "erlang", "perl"));
        tagDTOS.add(program);

        TagDTO framework = new TagDTO();
        framework.setCategory("平台框架");
        framework.setTags(Arrays.asList("laravel", "spring", "express", "django", "flask", "yii", "ruby-on-rails", "tornado", "koa", "struts"));
        tagDTOS.add(framework);


        TagDTO server = new TagDTO();
        server.setCategory("服务器");
        server.setTags(Arrays.asList("linux", "nginx", "docker", "apache", "ubuntu", "centos", "缓存 tomcat", "负载均衡", "unix", "hadoop", "windows-server"));
        tagDTOS.add(server);

        TagDTO db = new TagDTO();
        db.setCategory("数据库");
        db.setTags(Arrays.asList("mysql", "redis", "mongodb", "sql", "oracle", "nosql memcached", "sqlserver", "postgresql", "sqlite"));
        tagDTOS.add(db);

        TagDTO tool = new TagDTO();
        tool.setCategory("开发工具");
        tool.setTags(Arrays.asList("git", "github", "visual-studio-code", "vim", "sublime-text", "xcode intellij-idea", "eclipse", "maven", "ide", "svn", "visual-studio", "atom emacs", "textmate", "hg"));
        tagDTOS.add(tool);
        return tagDTOS;
    }

    /*
     * 
     * @description:此方法用于判断输入的标签是否合法，即输入的标签是否是标签列表中展示的
     * @param null
     * @return: 
     * @throws: 
     */
    public static String isValid(String tag){
        String[] split = StringUtils.split(tag, ",");
        List<TagDTO> validTags = get();
        //获取全部的合法标签
        List<String> allValidTags = validTags.stream().flatMap(t -> t.getTags().stream()).collect(Collectors.toList());
        //将不合法的标签过滤，并拼接成字符串
        String notValidTag = Arrays.stream(split).filter(s -> !allValidTags.contains(s)).collect(Collectors.joining(","));
        return notValidTag;
    }

}
