package lagou.sqlSession;

import lagou.pojo.Configuration;
import lagou.pojo.MappedStatement;

import java.util.List;

public class Insert implements SqlAction {
    private Configuration configuration;

    private String nameSpace;

    public Insert(Configuration configuration, String nameSpace) {
        this.configuration = configuration;
        this.nameSpace = nameSpace;
    }

    @Override
    public <T> T execute(Class<?> mapperClass) throws Exception {
        simpleExecutor simpleExecutor = new simpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(nameSpace + this.getClass().getSimpleName());
        simpleExecutor.query(configuration, mappedStatement, mapperClass);
        return null;
    }
}
