package service;

import commons.Page;
import domain.Customer;
import exception.IdIsNullEmpty;

import java.util.List;

public interface BusinessService {

    /**
     * 返回所有的客户信息
     * @return
     *
     * 请使用 Page findPage(String num);
     */
    @Deprecated
    List<Customer> findAllCustomers();

    /**
     * 添加新用户
     * @param customer
     */
    void addCustomer(Customer customer);

    /**
     * 根据用户ID 删除用户
     * @param customerID
     */
    void deleteCoustomer(Integer customerID);

    /**
     * 更新客户信息
     * @param customer
     * @throws IdIsNullEmpty    如果参数的ID值为null, 抛出此异常
     */
    void updateCustomer(Customer customer) throws IdIsNullEmpty;

    /**
     * 根据主键查询客户信息
     * @param customerID
     * @return
     */
    Customer findCustomerById(Integer customerID);

    /**
     * 根据页码查询分页信息的page 对象
     * @param num
     * @return
     */
    Page findPage(String num);
}
