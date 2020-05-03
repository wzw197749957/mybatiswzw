package lagou.dao;


import lagou.pojo.User;

import java.util.List;

//请完善自定义持久层框架IPersistence，在现有代码基础上添加、修改及删除功能。【需要采用getMapper方式】

public interface IUserDao {

    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;

    public void insert(User user) throws Exception;

    public void updateById(User user) throws Exception;

    public void deleteById(User user) throws Exception;


}
