package io.yiduspace.community.service;

import io.yiduspace.community.dto.GithubUserDto;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdateUser(GithubUserDto githubUser) {
        User user = userMapper.getUserByAccountId(githubUser.getId());
        if(user == null){
            //插入
            user = new User();
            user.setAccountId(githubUser.getId());
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insertUser(user);
        }else{
            //更新
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setGmtModified(System.currentTimeMillis());
            user.setToken(UUID.randomUUID().toString());
            userMapper.updateUser(user);
        }
    }

    public User getUserByAccountId(long accountId) {
        User user = userMapper.getUserByAccountId(accountId);
        return user;
    }
}
