package web.controller;


import commons.Page;
import domain.Customer;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.springframework.mock.web.MockHttpServletRequest;
import service.BusinessService;
import service.impl.BusinessServiceImpl;
import util.FillBeanUtil;
import web.bean.CustomerFormBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

public class Controller extends HttpServlet {

    private BusinessService service = new BusinessServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String encoding = "UTF-8";
        req.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset="+encoding);
        String op = req.getParameter("op");

        if ("showAllCustomers" .equals(op)){
            showAllCustomers(req, resp);
        }else if ("addCustomer" .equals(op)){
            addCustomer(req, resp);
        }else if ("editUI".equals(op)){
            editUI(req, resp);
        }else if("deleOne".equals(op)){
            deleOne(req, resp);
        }
    }

    // 删除一个用户
    private void deleOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String customerID = req.getParameter("customerId");
        service.deleteCoustomer(Integer.valueOf(customerID));
        resp.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort());
    }

    // 显示要修改记录的原数据
    private void editUI(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String customerID = req.getParameter("customerId");
        Customer c = service.findCustomerById(Integer.valueOf(customerID));
        req.setAttribute("c", c);
        req.getRequestDispatcher("/editCustomers.jsp").forward(req, resp);
    }

    private void addCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 将数据封装到 formBean
        CustomerFormBean formBean = FillBeanUtil.fillBean(req,CustomerFormBean.class);
        if (!formBean.validate()){
            // 回显
            req.setAttribute("formBean",formBean);
            req.getRequestDispatcher("/addCustomer.jsp").forward(req, resp);

            return;
        }else {
            // 填充模型: 生日的类型不匹配, 爱好也不匹配
            ConvertUtils.register(new DateLocaleConverter(), Date.class);

            Customer customer = new Customer();
            try {
                BeanUtils.copyProperties(customer, formBean);
            } catch (Exception e) {
                resp.getWriter().write("服务器忙");
                return;
            }

            // 处理爱好
            String hobbies[] = formBean.getHobbies();
            if (hobbies != null && hobbies.length > 0){
                StringBuffer sb = new StringBuffer();
                for (int i=0; i<hobbies.length;i++){
                    if (i>0){
                        sb.append(",");
                    }
                    sb.append(hobbies[i]);
                }
                customer.setHobby(sb.toString());
            }
            service.addCustomer(customer);
            String path = req.getContextPath();
            // 重定向跳转到首页
            resp.sendRedirect(req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort());
            return;
        }
    }

    // 查询所有的客户
    private void showAllCustomers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Customer> customers = service.findAllCustomers();
        String num = req.getParameter("num");
        Page page = service.findPage(num);
//        req.setAttribute("customers",customers);
        page.setUri("");

        req.setAttribute("page",page);
        req.getRequestDispatcher("/listCustomers.jsp").forward(req, resp);

    }
}
