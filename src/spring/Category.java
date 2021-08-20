package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author mo7984130
 * @Classname Category
 * @Description TODO
 * @Date 2021/8/20 3:52 下午
 */
public class Category {

    private int id;
    private String name;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" });
        Category category = (Category) context.getBean("c");
        System.out.println(category.getName());
    }

}
