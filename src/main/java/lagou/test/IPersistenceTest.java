package lagou.test;


import com.alibaba.fastjson.JSONObject;
import lagou.dao.IUserDao;
import lagou.io.Resources;
import lagou.pojo.User;
import lagou.sqlSession.SqlSession;
import lagou.sqlSession.SqlSessionFactory;
import lagou.sqlSession.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IPersistenceTest {

    @Test
    public void test() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //调用
        List<User> users = sqlSession.selectList("lagou.dao.IUserDao.findAll", null);

        System.out.println(JSONObject.toJSONString(users));

       /* List<User> users = sqlSession.selectList("user.selectList");
        for (User user1 : users) {
            System.out.println(user1);
        }*/

//        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
//
//        List<User> all = userDao.findAll();
//        for (User user1 : all) {
//            System.out.println(user1);
//        }


    }

    @Test
    public void testInsert() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("wzw");
        user.setPassword("1234");
        user.setBirthday("2020-05-03");
        //调用
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        iUserDao.insert(user);
    }

    @Test
    public void testUpdate() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("wzw");
        user.setPassword("4321");
        user.setBirthday("2020-05-03");
        //调用
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        iUserDao.updateById(user);
    }

    @Test
    public void testDelete() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsSteam);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = new User();
        user.setId(3);
        user.setUsername("wzw");
        user.setPassword("1234");
        user.setBirthday("2020-05-03");
        //调用
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        iUserDao.deleteById(user);
    }


}
