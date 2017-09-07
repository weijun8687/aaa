package service.impl;

import commons.Page;
import dao.CustomerDao;
import dao.impl.CustomerDaoMySqlImpl;
import domain.Customer;
import exception.IdIsNullEmpty;
import service.BusinessService;

import java.util.List;

public class BusinessServiceImpl implements BusinessService {

    private CustomerDao cDao = new CustomerDaoMySqlImpl();

    @Override
    public List<Customer> findAllCustomers() {
        return cDao.findAll();
    }

    @Override
    public void addCustomer(Customer customer) {
        cDao.addCustomer(customer);
    }

    @Override
    public void deleteCoustomer(Integer customerID) {
        cDao.deleteCustomer(customerID);
    }

    @Override
    public void updateCustomer(Customer customer) throws IdIsNullEmpty {
        if (customer.getId() == null){
            throw new IdIsNullEmpty("the customer's is can not empty");
        }

        cDao.updateCutomer(customer);

    }

    @Override
    public Customer findCustomerById(Integer customerID) {
        return cDao.findCustomerById(customerID);
    }

    @Override
    public Page findPage(String num) {
        int pageNum = 1;
        if (num!=null&&!"".equals(num)){
            pageNum = Integer.parseInt(num);
        }
        int totalRecodes = cDao.getTotalRecodes();
        Page page = new Page(pageNum, totalRecodes);
        List recodes = cDao.findPageRecodes(page.getStartIndex(), page.getPageSize());
        page.setRecords(recodes);
        return page;
    }
}
