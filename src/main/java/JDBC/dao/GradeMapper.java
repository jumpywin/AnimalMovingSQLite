package JDBC.dao;

import JDBC.model.Grade;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

public interface GradeMapper {

    @Select("select * from grade where id =#{id}")
    Grade FindGradeById(int id);

    @Select("select * from grade where id =(select id from user where nick = #{nick})")
    List<Grade> FindGradeByNick(String nick);

    @Select("Select * from grade where difficulty =#{difficulty} order by completion_time, userid limit 10")
    List<Grade> getRankingList(int difficulty);


    @Insert("insert into grade(`userid`,difficulty,completion_time) values(#{userid},#{difficulty},#{completion_time}) ")
    void InsertGrade(@Param("userid") int userid, @Param("difficulty") int difficulty, @Param("completion_time") int completion_time);


    //todo 不急，需要做一个减少记录数据的操作，删除成绩记录中同一个人的后一半数据
}