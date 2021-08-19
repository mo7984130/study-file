package interfaces;

import java.util.List;

/**
 * @author mo7984130
 * @Classname DAO
 * @Description TODO
 * @Date 2021/8/10 9:19 下午
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public interface DAO<T> {

    /**
     * 根据对象添加数据进数据库中
     * @param t 对象
     */
    void add(T t);

    /**
     * 根据对象删除数据库中的数据
     * @param t 对象
     */
    void delete(T t);

    /**
     * 根据对象更新数据库中的数据
     * @param t 对象
     */
    void update(T t);

    /**
     * 将数据库中的数据存入容器中并返回
     * @return 一个包含这数据库数据的容器
     */
    List list();

}
