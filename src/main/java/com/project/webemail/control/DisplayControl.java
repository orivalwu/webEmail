package com.project.webemail.control;

import com.project.webemail.mapper.keyWordMapper;
import com.project.webemail.service.PopService;
import com.project.webemail.vo.Post;
import com.project.webemail.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import javax.mail.Address;
import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class DisplayControl {
    @Autowired
    HttpServletRequest request;
    @Autowired
    keyWordMapper mapper;

    @RequestMapping("/read")
    @ResponseBody
    public List<Post> display() throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        PopService service = new PopService();
        //登录imap服务器
        service.login(user.getEmail(),user.getAuthCode());
        List<Post>list = new ArrayList<>();
        Message[] messages = service.getMessages();
        int messageCount = service.getMessageCount();
        //如果SMTP邮箱中的邮件数目不等于当前数据库中邮件数目，就执行更新操作
        if(mapper.getCount() != messageCount){
            //先删除本地数据库中的所有邮件
            mapper.Delete();
            for(int i=0; i<messageCount; i++){
                String subject = "null";
                if(messages[i].getSubject() != null){
                    subject =messages[i].getSubject();
                }
                Address[] fromAddress = messages[i].getFrom();
                String fromTemp = fromAddress[0].toString();
                String reg = "<(.+)>";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(fromTemp);
                String from = "";
                while(matcher.find()){
                    from = matcher.group(1);
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String sentDate = sdf.format(messages[i].getReceivedDate());
                Object temp = messages[i].getContent();
                String content="";
                if(temp instanceof String){
                    content = (String) temp;
                }
                String description = content.length()>20?(content.substring(0,20)+"..."):content;
                Post item = new Post(subject,sentDate,from,content,description);
                //将数据插入到数据库中
                mapper.insert(item);
                list.add(item);
            }
        }else{
            //否则直接在本地数据库中进行查询操作
            list = mapper.queryAll();
            Collections.sort(list);
        }
        request.getSession().setAttribute("list",list);
        return list;
    }

    /*
     * @param: [nowPage]
     * @return: java.util.List<com.project.webemail.vo.Post>
     * @description:实现分页显示
     */
    @RequestMapping("/rread/{nowPage}")
    @ResponseBody
    public List<Post>query(@PathVariable int nowPage){
        List<Post>list = (List<Post>) request.getSession().getAttribute("list");
        int nowIndex = (nowPage-1)*7;
        int lastIndex = nowPage*7;
        if(nowIndex > list.size()-1 || lastIndex > list.size()-1){
            return null;
        }
        return list.subList(nowIndex,lastIndex);
    }


    @RequestMapping("/query/{key}")
    @ResponseBody
    public List<Post>queryByKey(@PathVariable String key){
        return mapper.queryByKey(key);
    }
    @RequestMapping("/getContent/{id}")
    public ModelAndView getContent(@PathVariable int id){
        ModelAndView mav = new ModelAndView("detial");
        mav.addObject("item",mapper.getMsg(id));
        return mav;
    }

}
