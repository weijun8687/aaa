package util;

import com.sun.xml.internal.ws.resources.HttpserverMessages;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.mock.web.MockHttpServletRequest;
import web.bean.CustomerFormBean;

import javax.servlet.http.HttpServletRequest;

public class FillBeanUtil {

    // 将请求 request 转换成 模型
    public static <T> T fillBean(HttpServletRequest request, Class<CustomerFormBean> clazz){
        try {
            T bean = (T) clazz.newInstance();
            BeanUtils.populate(bean, request.getParameterMap());
            return bean;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
