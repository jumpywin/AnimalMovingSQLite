package JDBC.dao;

import JDBC.model.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;

public class UserTools {

    /**
     * @param id
     * @return User
     * @throws IOException
     * @Description 通过id来寻找用户
     */
    public static User findUserById(int id) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param nick
     * @return User
     * @throws IOException
     * @Description 通过Nick来寻找用户
     */

    public static User findUserByNick(String nick) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findUserByNick(nick);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    /**
     * @param id
     * @return String
     * @throws IOException
     * @Description 通过Nick来寻找用户
     */

    public static String findNickById(int id) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            return userMapper.findNickById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * @param nick，passwd
     * @return User
     * @throws IOException
     * @Description 通过账户密码来创建用户
     * todo 没有获取创建时间， 最后一次登录时间也没有做
     */
    public static void createAccout(String nick, String passwd) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.createAccount(nick, passwd);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    /**
     * @param id
     * @return User
     * @throws IOException
     * @Description 通过id来删除用户
     */
    public static void deleteAccount(int id) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.deleteAccount(id);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    /*
        更改用户密码
     */
    public static void altPasswd(int id, String passwd) throws IOException {
        //通过会话工厂得到Sqlsession
        try (SqlSession sqlSession = SqlSessionManger.getSqlSessionFactory().openSession()) {
            //通过sqlSession来操作数据库
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.altPasswd(id, passwd);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}