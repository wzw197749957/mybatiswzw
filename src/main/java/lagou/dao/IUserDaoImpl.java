package lagou.dao;

import lagou.pojo.User;
import lagou.sqlSession.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class IUserDaoImpl implements IUserDao {
    @Override
    public List<User> findAll() throws Exception {
        return null;
    }

    @Override
    public User findByCondition(User user) throws Exception {
        return null;
    }

    @Override
    public void insert(User user) throws Exception {

    }

    @Override
    public void updateById(User user) throws Exception {

    }

    @Override
    public void deleteById(User user) throws Exception {

    }


}
