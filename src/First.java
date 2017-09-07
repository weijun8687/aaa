import com.mysql.jdbc.*;
import domain.User;
import util.JDBCUtil;
//import com.mysql.jdbc.Statement;

import java.util.ArrayList;
import java.util.List;

public class First {

    public static void main(String[] args) {
        Connection connection = null;
        Statement stem = null;
        ResultSet resultSet = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            // 所有与数据库的交互都是基于链接的
//            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=root");

            connection = JDBCUtil.getConnection();

            // 创建SQL语句的对象
            stem = (Statement) connection.createStatement();
            // b 代表的是有没有结果集, 不是语句正不正确
            boolean b = stem.execute("SELECT * FROM user");
            resultSet = (ResultSet) stem.executeQuery("SELECT * FROM user");

            // 改句返回的是 执行下面的语句影响的数据条数
//        int num = stem.executeUpdate("UPDATE user SET password '123'");

            List<User> users = new ArrayList<User>();

            while (resultSet.next()){
//            System.out.print(resultSet.getObject("id"));
//            System.out.print("  " + resultSet.getObject("name"));
//            System.out.print("  " + resultSet.getObject("sex"));
//            System.out.println();

                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setSex(resultSet.getBoolean("sex"));
                users.add(user);
            }
            for (User u : users){
                System.out.println(u);
            }

            System.out.print(b);

            // 常用方法
            resultSet.next();   // 向下移动
            resultSet.previous();   // 向上移动
            resultSet.absolute(2);  // 查看是否有第二条记录
            resultSet.beforeFirst();    // 移动到第一条记录的前面. 默认位置 没有返回值
            resultSet.afterLast();  // 移动到最后一条数据的后面
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {

            JDBCUtil.release(resultSet, stem, connection);

        }

    }

}


/*

 */