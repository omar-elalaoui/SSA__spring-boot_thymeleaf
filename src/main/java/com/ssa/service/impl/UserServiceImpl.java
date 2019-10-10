package com.ssa.service.impl;

import com.ssa.dao.UserRepository;
import com.ssa.entity.Role;
import com.ssa.entity.User;
import com.ssa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public void save(User user) {
        List<Role> roles= new ArrayList<Role>();
        Role user_role= new Role();
        user_role.setRole("USER");
        roles.add(user_role);
        user.setRoles(roles);
        user.setUncryptedPwd(user.getUncryptedPwd());
        user.setPwd(passwordEncoder.encode(user.getUncryptedPwd()));
        user.setActive(true);
        userRepository.save(user);
    }
    
    @Override
    public void update_user_profile(User user) {
        userRepository.save(user);
    }
    
    @Override
    public void update_admin_code(String admin_username, String code) {
        User user= findById(admin_username);
        user.setCodePrev(code);
        update_user_profile(user);
    }
    
    @Override
    public boolean update_admin_pwd(String nv_pwd, String nv_pwd_2) {
        if(!nv_pwd.equals(nv_pwd_2)){
            return false;
        }
        User user=findById("admin");
        user.setPwd(passwordEncoder.encode(nv_pwd));
        update_user_profile(user);
        return true;
    }
    
    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }
    
    @Override
    public User findById(String username) {
        return userRepository.findById(username).get();
        
    }
    
    @Override
    public String getcurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserName = authentication.getName();
            return currentUserName;
    }
    
    @Override
    public List<User> findAll() {
        List<User> users= userRepository.findAll();
        users.remove(userRepository.findById("admin").get());
        return users;
    }
}
