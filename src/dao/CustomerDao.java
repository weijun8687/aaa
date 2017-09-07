package dao;

import domain.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAll();

    void addCustomer(Customer customer);

    void deleteCustomer(Integer customerID);

    void updateCutomer(Customer customer);


    Customer findCustomerById(Integer customerID);

    int getTotalRecodes();

    List findPageRecodes(int startIndex, int pageSize);
}
