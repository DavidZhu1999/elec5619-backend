package com.freshshare.customer.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.freshshare.customer.exception.CustomerException;
import com.freshshare.customer.exception.CustomerExceptionEnum;
import com.freshshare.customer.mapper.CustomerMapper;
import com.freshshare.customer.request.CustomerLogInRequestParam;
import com.freshshare.customer.request.CustomerSignUpRequestParam;
import com.freshshare.customer.service.CustomerService;
import com.freshshare.entity.Customer;
import com.freshshare.util.StpCustomerUtil;
import com.freshshare.util.StpInterfaceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the service implementation for customer auth
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Value("${google.api.key}")
    private String API_KEY; // google api key

    @Value("${google.api.url}")
    private String BASE_URL; // google api url

    @Value("${auth.keys.privateKey}")
    private String privateKey; // private key

    @Value("${auth.keys.publicKey}")
    private String publicKey; // public key

    /**
     * this is signup method for customer
     * @param param
     */
    @Override
    @Transactional
    public void signUp(CustomerSignUpRequestParam param) {
        Customer customer = this.getOne(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getCustomerUsername, param.getCustomerUsername())
        );
        if (customer != null) {
            throw new CustomerException(CustomerExceptionEnum.SIGN_UP_EXIST_USERNAME);
        }
        Customer customer1 = this.getOne(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getCustomerEmail, param.getCustomerEmail())
        );
        if (customer1 != null) {
            throw new CustomerException(CustomerExceptionEnum.SIGN_UP_EXIST_EMAIL);
        }

        String ciphertext;
        try {
            // use publickey to encrypt password
            ciphertext = SaSecureUtil.rsaEncryptByPublic(publicKey, param.getCustomerPassword());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Customer newOne = new Customer();
        newOne.setCustomerUsername(param.getCustomerUsername());
        newOne.setCustomerPassword(ciphertext);
        newOne.setCustomerAddress(param.getCustomerAddress());
        newOne.setCustomerEmail(param.getCustomerEmail());
        newOne.setCustomerPhone(param.getCustomerPhone());
        newOne.setCustomerFirstname(param.getCustomerFirstname());
        newOne.setCustomerLastname(param.getCustomerLastname());
        newOne.setCustomerCreateTime(LocalDateTime.now());
        newOne.setCustomerStatus("active");
        newOne.setCustomerPostcode(param.getCustomerPostcode());

        try {
            URL url = new URL(BASE_URL + param.getCustomerAddress().replace(" ", "%20") + "&key=" + API_KEY);
            try {
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream is = connection.getInputStream();
                byte[] bytes = new byte[1024];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                while ((len = is.read(bytes)) != -1) {
                    sb.append(new String(bytes, 0, len));
                }
                newOne.setCustomerGeocode(sb.toString());
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {
            this.save(newOne);
            log.info(newOne.getCustomerUsername() + " SignUp successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * this is login method for customer
     * @param param
     * @return
     */
    @Override
    public Map<Object,Object> logIn(CustomerLogInRequestParam param) {
        Customer customer = this.getOne(new LambdaQueryWrapper<Customer>()
                .eq(Customer::getCustomerUsername, param.getCustomerUsername())
        );

        if (customer == null){
            throw new CustomerException(CustomerExceptionEnum.LOG_IN_ERROR_NOT_ACCOUNT);
        }

        if (!customer.getCustomerStatus().equals("active")){
            throw new CustomerException(CustomerExceptionEnum.LOG_IN_ERROR_ACCOUNT_LOCKED);
        }

        try {
            if (SaSecureUtil.rsaDecryptByPrivate(privateKey,customer.getCustomerPassword()).equals(param.getCustomerPassword())){
                StpCustomerUtil.login(customer.getCustomerId(), param.getRememberMe());
                StpCustomerUtil.getTokenSession().set("customer_id",customer.getCustomerId());
                StpInterfaceImpl stpInterface = new StpInterfaceImpl();
                log.info(customer.getCustomerUsername() + " LogIn successfully");
            }
            else{
                throw new CustomerException(CustomerExceptionEnum.LOG_IN_ERROR_NOT_MATCH);
            }
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.LOG_IN_ERROR_NOT_MATCH);
        }

        Map<Object,Object> returnMap = new HashMap<>();
        returnMap.put("satoken",StpCustomerUtil.getTokenInfo());
        returnMap.put("rememberMe",param.getRememberMe());

        return returnMap;
    }

    /**
     * this is logout method for customer
     */
    @Override
    public void logOut() {
        try {
            if (StpCustomerUtil.hasRole("customer")){
                try {
                    StpCustomerUtil.logout();
                } catch (Exception e) {
                    throw new CustomerException(CustomerExceptionEnum.LOG_OUT_ERROR);
                }
            }
        } catch (Exception e) {
            throw new CustomerException(CustomerExceptionEnum.LOG_OUT_ERROR);
        }
    }
}
