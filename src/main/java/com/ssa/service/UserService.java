package com.ssa.service;

import com.ssa.entity.User;

import java.util.List;

public interface UserService {
    public void save(User user);
    public void update_user_profile(User user);
    public void update_admin_code(String admin_username, String code);
    public boolean update_admin_pwd(String nv_pwd, String nv_pwd_2);
    public void deleteById(String username);
    public User findById(String username);
    public String getcurrentUsername();
    public List<User> findAll();
}
