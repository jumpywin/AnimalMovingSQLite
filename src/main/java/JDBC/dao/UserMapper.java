package JDBC.dao;

import JDBC.model.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("select * from `user` where id = #{id}")
    User findUserById(@Param("id") int id);

    @Select("select * from`user` where nick = #{nick}")
    User findUserByNick(@Param("nick") String nick);

    @Select("select nick from `user` where id = #{id}")
    String findNickById(@Param("id") int id);

    @Insert("insert into user(nick,passwd) values(#{nick},#{passwd})")
    void createAccount(@Param("nick") String nick, @Param("passwd") String passwd);

    @Update("update user set passwd = #{passwd} where id =#{id}")
    void altPasswd(@Param("id") int id, @Param("passwd") String passwd);

    @Delete("delete from user where id =#{id}")
    void deleteAccount(@Param("id") int id);
}
