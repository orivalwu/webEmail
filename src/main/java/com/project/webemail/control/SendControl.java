package com.project.webemail.control;

import com.project.webemail.mapper.UserMapper;
import com.project.webemail.service.GroupPush;
import com.project.webemail.service.PushService;
import com.project.webemail.vo.Post;
import com.project.webemail.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SendControl {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/draw")
    public ModelAndView drawMsg(){
        return new ModelAndView("drawInterface");
    }

    @RequestMapping("/send")
    @ResponseBody
    public String sendMsg(@RequestBody Post post){
        HttpSession session = request.getSession();
        User items  = (User) session.getAttribute("user");
        System.out.println(post.toString());
        PushService.init(items.getEmail(),items.getAuthCode());
        return PushService.sendTextEmail(post.getTo(),post.getSubject(),post.getContent());
    }

    @RequestMapping("/groupSend")
    @ResponseBody
    public String groupSend(@RequestBody Post post){
        List<String>list = new ArrayList<>();
        list = Arrays.asList(post.getTo().split(";"));
        HttpSession session = request.getSession();
        User items  = (User) session.getAttribute("user");
        System.out.println(post.toString());
        PushService.init(items.getEmail(),items.getAuthCode());
        GroupPush thread1 = new GroupPush(list,post.getSubject(),post.getContent());
        GroupPush thread2 = new GroupPush(list,post.getSubject(),post.getContent());
        thread1.run();
        thread2.run();
        return "群发成功";
    }

}
