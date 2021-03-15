package com.project.webemail.mapper;

import com.project.webemail.vo.Post;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface keyWordMapper {
    //将远程数据库的内容更新到本地
    @Insert("insert into post(id,subject,sentDate,`from`,content,description)values(#{id},#{subject},#{sentDate},#{from},#{content},#{description})")
    int insert(Post post);

    //根据主题关键字查询信息
    @Select("select * from post where subject like concat('%',#{key},'%')")
    List<Post> queryByKey(@Param("key") String key);

    //查询所有的邮件信息
    @Select("select * from post")
    List<Post>queryAll();

    //查询某条邮件的信息
    @Select("select * from post where id = #{id}")
    Post getMsg(@Param("id") int id);

    //查询数据库中的邮件数目
    @Select("select count(*) from post")
    int getCount();

    //删除本地数据库中的所有邮件
    @Delete("delete from post")
    void Delete();
}
