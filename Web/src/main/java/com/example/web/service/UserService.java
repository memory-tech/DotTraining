package com.example.web.service;

import com.example.web.enm.Message;
import com.example.web.entity.Result;
import com.example.web.entity.User;
import com.example.web.repository.UserRepository;
import com.example.web.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author Memory
 * @Date 2021/7/7 16:38
 * @Version 1.0
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    /**
     * 注册
     * @param user
     * @return
     */
    public Result register(@RequestBody User user){
        User user0=userRepository.findByPhone(user.getPhone());
        if(user0!=null){
            return ResultUtils.error(Message.USER_IS_EXIST);
        }
        return ResultUtils.success(userRepository.save(user));
    }
    /**
     * 更新hr信息
     * @param user
     * @return
     */
    public Result UpdateOne(User user){
        if(user.getId()==null){
            return ResultUtils.error(Message.NO_ID);
        }
        userRepository.delete(userRepository.findById(user.getId()));
        return ResultUtils.success(userRepository.save(user));
    }

    /**
     * 删除hr信息
     * @param id
     * @return
     */
    public Result deleteOne(Integer id){
        User user = userRepository.findById(id);
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        userRepository.delete(user);
        return ResultUtils.success();
    }

    /**
     * 登录
     * @param user
     * @return
     */
    public Result login(@RequestBody User user){
        User user0=userRepository.findByPhone(user.getPhone());
        //查不到表示未注册
        if(user==null){
            return ResultUtils.error(Message.USER_NOT_EXIST);
        }
        //密码错误
        if(!user0.getPassword().equals(user.getPassword())){
            return ResultUtils.error(Message.USER_ERR_PASS);
        }
        return ResultUtils.success(user);
    }

}
