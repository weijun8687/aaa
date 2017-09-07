import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import org.junit.Assert;
import org.junit.Test;
import util.JDBCUtil;

public class TestDemo1 {

    @Test
    public void testAdd() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtil.getConnection();
            // 这样写不安全
//            statement = (Statement) connection.createStatement();
//            int num = statement.executeUpdate("INSERT INTO  user(id, name, sex) VALUES (3, '小王', TRUE )");

            // 安全
            statement = (PreparedStatement) connection.prepareStatement("INSERT INTO  user(id, name, sex) VALUES (?, ?, ? )");
            statement.setInt(1,3);
            statement.setString(2, "xiaosan");
            statement.setBoolean(3, true);
            int num = statement.executeUpdate();

            Assert.assertEquals(1, num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }

    }

    @Test
    public void testUpdate() {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = (Statement) connection.createStatement();
            int num = statement.executeUpdate("UPDATE user SET name='xiaowang' WHERE id=3");
            Assert.assertEquals(1, num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Test
    public void testFind() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = (Statement) connection.createStatement();
            resultSet = (ResultSet) statement.executeQuery("SELECT * FROM user WHERE id=3");

            if (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

//            Assert.assertEquals(1, num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Test
    public void testDelete() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            statement = (Statement) connection.createStatement();
            int num = statement.executeUpdate("DELETE FROM user WHERE id=3");
            Assert.assertEquals(1, num);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }
}
