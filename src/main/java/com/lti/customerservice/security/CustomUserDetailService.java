package com.lti.customerservice.security;

import com.lti.customerservice.entity.CustomerDetails;
import com.lti.customerservice.repository.CustomerDetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("customUserService")
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private CustomerDetailsRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         final CustomerDetails customer = customerRepository.findByUsername(username);
         if(customer ==null){
             throw new UsernameNotFoundException(username);
         }
        UserDetails user = User.withUsername(customer.getEmail()).password(customer.getPassword()).authorities("USER").build();
        return user;
    }
}
