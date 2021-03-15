package com.project.webemail.control;

import com.project.webemail.mapper.UserMapper;
import com.project.webemail.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserControl {
    @Autowired
    UserMapper userMapper;

    @RequestMapping("/validate")
    @ResponseBody
    public String loginValidate(@RequestBody User user, HttpServletRequest request){
        User temp = null;
        temp = userMapper.findByName(user);
        if(temp == null){
            return "用户不存在";
        }else{
            if(userMapper.validate(user) != null){
                //将user对象加入到session中
                HttpSession session = request.getSession();
                session.setAttribute("user",temp);
                return "登录成功";
            }else{
                return "密码错误";
            }
        }
    }

    @RequestMapping("/reg")
    @ResponseBody
    public String register(@RequestBody User user){
        System.out.println(user.toString());
        User temp = null;
        if(user.getUserName()==null || user.getUserName().equals("")){
            return "用户名不能为空";
        }
        if(user.getPassWord()==null || user.getPassWord().equals("")){
            return "密码不能为空";
        }
        if(user.getEmail()==null || user.getEmail().equals("")){
            return "邮箱不能为空";
        }
        if(user.getAuthCode()==null || user.getAuthCode().equals("")){
            return "授权码不能为空";
        }
        temp = userMapper.findByName(user);
        if(temp != null){
            return "用户名已存在";
        }
        else{
            int count = userMapper.addUser(user);
            if(count == 1){
                return "注册成功";
            }
            else return "注册失败";
        }
    }
}
