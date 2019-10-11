package com.ssa.service.impl;

import com.ssa.dao.NoteRepository;
import com.ssa.dao.ProfileRepository;
import com.ssa.dao.UserRepository;
import com.ssa.entity.Note;
import com.ssa.entity.Profile;
import com.ssa.entity.Role;
import com.ssa.entity.User;
import com.ssa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    NoteRepository noteRepository;
    
    @Override
    public void save(User user) {
        if(findById(user.getUsername()) == null){
            List<Role> roles= new ArrayList<Role>();
            Role user_role= new Role();
            user_role.setRole("USER");
            roles.add(user_role);
            user.setRoles(roles);
        }else{
            user.setRoles(findById(user.getUsername()).getRoles());
        }
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
    public void add_user_note(long profile_id, String note_text) {
        Profile profile= profileRepository.findById(profile_id).get();
        profile.getNotes().add(new Note(0, note_text, new Date()));
        profileRepository.save(profile);
    }
    
    @Override
    public void deleteById(String username) {
        userRepository.deleteById(username);
    }
    
    @Override
    public void deleteNoteById(long id) {
        noteRepository.deleteById(id);
    }
    
    @Override
    public User findById(String username) {
        Optional optional= userRepository.findById(username);
        if(optional.isPresent())
            return (User)optional.get();
        return null;
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
