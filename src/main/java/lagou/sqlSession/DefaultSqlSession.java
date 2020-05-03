package lagou.sqlSession;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lagou.pojo.Configuration;
import lagou.pojo.MappedStatement;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

    private static final Map<String, SqlAction> actMap = Maps.newHashMap();

    ;

    private Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        actMap.put("insert", new Insert(configuration, this.getClass().getSimpleName()));
        actMap.put("updateById", new UpdateById());
        actMap.put("deleteById", new DeleteById());
    }


    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws Exception {

        //将要去完成对simpleExecutor里的query方法的调用
        simpleExecutor simpleExecutor = new simpleExecutor();
        System.out.println(JSONObject.toJSONString(configuration.getMappedStatementMap()));
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        List<Object> list = simpleExecutor.query(configuration, mappedStatement, params);

        return (List<E>) list;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws Exception {
        List<Object> objects = selectList(statementid, params);
        if (null != objects
                && objects.size() == 1) {
            return (T) objects.get(0);
        } else {
            return null;
        }


    }

    @Override
    public <T> T getMapper(Class<?> mapperClass) {
        // 使用JDK动态代理来为Dao接口生成代理对象，并返回

        Object proxyInstance = Proxy.newProxyInstance(DefaultSqlSession.class.getClassLoader(), new Class[]{mapperClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 底层都还是去执行JDBC代码 //根据不同情况，来调用selctList或者selectOne
                // 准备参数 1：statmentid :sql语句的唯一标识：namespace.id= 接口全限定名.方法名
                // 方法名：findAll
//                if (actMap.containsKey(method.getName())) {
//                    return actMap.get(method.getName()).execute(args[0].getClass());
//                }
                String methodName = method.getName();
                String className = method.getDeclaringClass().getName();

                String statementId = className + "." + methodName;

                // 准备参数2：params:args
                // 获取被调用方法的返回值类型
                Type genericReturnType = method.getGenericReturnType();
                // 判断是否进行了 泛型类型参数化
                if (genericReturnType instanceof ParameterizedType) {
                    List<Object> objects = selectList(statementId, args);
                    return objects;
                }

                return selectOne(statementId, args);

            }
        });

        return (T) proxyInstance;
    }

    @Override
    public void insert(String statementid, Object... params) throws Exception {
        selectList(statementid, params);
    }

    @Override
    public void updateById(String statementid, Object... params) throws Exception {

    }

    @Override
    public void deleteById(String statementid, Object... params) throws Exception {

    }


}
