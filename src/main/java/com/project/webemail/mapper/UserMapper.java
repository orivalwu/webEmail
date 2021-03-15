package com.project.webemail.mapper;

import com.project.webemail.vo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from users where userName = #{userName} and passWord = #{passWord}")
    User validate(User user);

    @Insert("insert into users(userName,passWord,Email,authCode)values(#{userName},#{passWord},#{email},#{authCode})")
    int addUser(User user);

    @Select("select * from users where userName = #{userName}")
    User findByName(User user);
}
