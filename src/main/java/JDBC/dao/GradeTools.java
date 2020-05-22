package JDBC.dao;

import JDBC.model.Grade;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;

public class GradeTools {

    /**
     * 游戏完成添加成绩进数据库
     */
    public static void InsertGrade(Grade grade) {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            GradeMapper GradeMapper = sqlSession.getMapper(GradeMapper.class);
            GradeMapper.InsertGrade(grade.getUserid(), grade.getDifficulty(), grade.getCompletion_time());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 通过成绩id查找成绩
     */
    public static Grade findGradeById(int id) {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            GradeMapper GradeMapper = sqlSession.getMapper(GradeMapper.class);
            return GradeMapper.FindGradeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过用户名查找成绩
     */
    public static List<Grade> findGradeBynick(String nick) {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            GradeMapper GradeMapper = sqlSession.getMapper(GradeMapper.class);
            return GradeMapper.FindGradeByNick(nick);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 给出游戏难度，获取排行榜 前十个
     */
    public static List<Grade> getRankingList(int difficulty) {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            GradeMapper GradeMapper = sqlSession.getMapper(GradeMapper.class);
            return GradeMapper.getRankingList(difficulty);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
