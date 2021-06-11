package io.yiduspace.community.service;

import io.yiduspace.community.dto.GithubUserDto;
import io.yiduspace.community.mapper.UserMapper;
import io.yiduspace.community.model.User;
import io.yiduspace.community.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrUpdateUser(GithubUserDto githubUser) {
        //User user = userMapper.getUserByAccountId(githubUser.getId());
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(githubUser.getId());
        List<User> users = userMapper.selectByExample(example);
        if(users.size() == 0){
            //插入
            User user = new User();
            user.setAccountId(githubUser.getId());
            user.setName(githubUser.getName());
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            userMapper.insert(user);
        }else{
            //更新
            User user = users.get(0);
            user.setName(githubUser.getName());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setGmtModified(System.currentTimeMillis());
            user.setToken(UUID.randomUUID().toString());
            userMapper.updateByPrimaryKey(user);
        }
    }

    public User getUserByAccountId(Long accountId) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountIdEqualTo(accountId);
        User user = userMapper.selectByExample(example).get(0);
        return user;
    }
}
