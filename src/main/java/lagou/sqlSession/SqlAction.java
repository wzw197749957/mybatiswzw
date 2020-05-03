package lagou.sqlSession;

public interface SqlAction {
    public <T> T execute(Class<?> mapperClass) throws Exception;
}
