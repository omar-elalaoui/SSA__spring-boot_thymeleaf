package com.ssa.controller;

import com.ssa.entity.Profile;
import com.ssa.entity.User;
import com.ssa.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
    
    @GetMapping("/users/add")
    public String users_form(Model model) {
        User user= new User();
        user.setProfile(new Profile());
        model.addAttribute("user", user);
        return "users_form";
    }
    
    @GetMapping("/users/{username}/edit")
    public String users_edit(Model model, @PathVariable(value="username") String username) {
        model.addAttribute("user", userService.findById(username));
        return "users_form";
    }
    
    @GetMapping("/users/delete")
    public String delete(String user, Model model) {
        userService.deleteById(user);
        return "redirect:/users";
    }
    
    
    @PostMapping("/users/add")
    public String utilisateurs_submit(User user) {
        userService.save(user);
        return "redirect:/users";
    }
    
    @GetMapping("/users/profile_ov")
    public String users_profile(Model model) {
        User user= userService.findById(userService.getcurrentUsername());
        model.addAttribute("user", user);
        return "users_profile_ov";
    }
    
    @GetMapping("/users/profile_param")
    public String users_profileparam(Model model) {
        User user= userService.findById(userService.getcurrentUsername());
        model.addAttribute("user", user);
        return "users_profile_param";
    }
    
    @PostMapping("/users/profile/edit_admin_code")
    public String utilisateurs_editr(String admin_username, String code_prev) {
        userService.update_admin_code(admin_username,code_prev);
        return "redirect:/users/profile_param";
    }
    
    @PostMapping("/users/profile/edit_admin_pwd")
    public String utilisateurs_pwd(Model model, String nv_pwd, String nv_pwd_2) {
        if(!userService.update_admin_pwd(nv_pwd, nv_pwd_2)){
            model.addAttribute("error_pwd", true);
        }
        User user= userService.findById(userService.getcurrentUsername());
        model.addAttribute("user", user);
        return "users_profile_param";
    }
    
    
}
