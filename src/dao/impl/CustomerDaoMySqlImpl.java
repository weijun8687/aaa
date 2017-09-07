package dao.impl;

import dao.CustomerDao;
import domain.Customer;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            statement = connection.prepareStatement("select * FROM customers limit ?,?");
            statement.setInt(1, startIndex);
            statement.setInt(2, pageSize);
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
}
