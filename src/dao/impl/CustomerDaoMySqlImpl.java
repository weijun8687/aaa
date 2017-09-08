package dao.impl;

import dao.CustomerDao;
import domain.Customer;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoMySqlImpl implements CustomerDao {

    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    @Override
    public List<Customer> findAll() {

        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("SELECT * FROM customers");
            resultSet = statement.executeQuery();
            List<Customer> customers = new ArrayList<Customer>();
            while (resultSet.next()) {
                Customer customer = new Customer();

                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setGender(resultSet.getString("gender"));
                customer.setBirthday(resultSet.getDate("birthday"));
                customer.setCellphone(resultSet.getString("cellphone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setHobby(resultSet.getString("hobby"));
                customer.setType(resultSet.getString("type"));
                customer.setDescription(resultSet.getString("description"));

                customers.add(customer);
            }
            return customers;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Override
    public void addCustomer(Customer customer) {

        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("INSERT INTO customers (name, gender, birthday, cellphone, email, hobby, type, description) VALUES (?,?,?,?,?,?,?,?)");
//            name, gender, birthday, cellphone, email, hobby, type, descr
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getGender());
            // 注意日期的设置
            statement.setDate(3, new Date(customer.getBirthday().getTime()));
            statement.setString(4, customer.getCellphone());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getHobby());
            statement.setString(7, customer.getType());
            statement.setString(8, customer.getDescription());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Override
    public void deleteCustomer(Integer customerID) {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("DELETE FROM customers WHERE id=?");
            statement.setInt(1, customerID);
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Override
    public void updateCutomer(Customer customer) {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("UPDATE customers SET name=?,gender=?,birthday=?,cellphone=?,email=?,hobby=?,type=?,description=? WHERE id=?");
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getGender());
            // 注意日期的设置
            statement.setDate(3, new Date(customer.getBirthday().getTime()));
            statement.setString(4, customer.getCellphone());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getHobby());
            statement.setString(7, customer.getType());
            statement.setString(8, customer.getDescription());
            statement.setInt(9, customer.getId());
            statement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Override
    public Customer findCustomerById(Integer customerID) {

        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("SELECT *FROM customers WHERE id=?");
            statement.setInt(1, customerID);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setGender(resultSet.getString("gender"));
                customer.setBirthday(resultSet.getDate("birthday"));
                customer.setCellphone(resultSet.getString("cellphone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setHobby(resultSet.getString("hobby"));
                customer.setType(resultSet.getString("type"));
                customer.setDescription(resultSet.getString("description"));
                return customer;
            }
            return null;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }

    }

    @Override
    public int getTotalRecodes() {
        try {
            connection = JDBCUtil.getConnection();
            statement = connection.prepareStatement("select count(*) FROM customers");
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtil.release(resultSet, statement, connection);
        }
    }

    @Override
    public List findPageRecodes(int startIndex, int pageSize) {
        try {
            connection = JDBCUtil.getConnection();

//            Connection.TRANSACTION_NONE  : 0;
//            Connection.TRANSACTION_READ_UNCOMMITTED  : 1
//            Connection.TRANSACTION_READ_COMMITTED  :2
//            Connection.TRANSACTION_REPEATABLE_READ  : 4
//            Connection.TRANSACTION_SERIALIZABLE     : 8
            // 设置隔离的级别
//            connection.setTransactionIsolation(Connection.TRANSACTION_NONE);
//            connection.setAutoCommit(false);    // 开启事务
//            Savepoint savepoint = null; // 设置回滚点

//            在事务中可以添加多条语句

            statement = connection.prepareStatement("select * FROM customers limit ?,?");
            statement.setInt(1, startIndex);
            statement.setInt(2, pageSize);
//            // 将语句添加到事务中
//            statement.addBatch();
//            statement.executeBatch();   // 提交事务

            // 回滚点的初始化
//            savepoint = connection.setSavepoint("a");

            resultSet = statement.executeQuery();

            List<Customer> customers = new ArrayList<Customer>();
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt("id"));
                customer.setName(resultSet.getString("name"));
                customer.setGender(resultSet.getString("gender"));
                customer.setBirthday(resultSet.getDate("birthday"));
                customer.setCellphone(resultSet.getString("cellphone"));
                customer.setEmail(resultSet.getString("email"));
                customer.setHobby(resultSet.getString("hobby"));
                customer.setType(resultSet.getString("type"));
                customer.setDescription(resultSet.getString("description"));
                customers.add(customer);
            }
            return customers;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
//            try {
//                //
//                connection.commit();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
            JDBCUtil.release(resultSet, statement, connection);
        }
    }
}
