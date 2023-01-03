package Sportify.securityservice.service;

import Sportify.securityservice.entities.AppRole;
import Sportify.securityservice.entities.AppUser;
import Sportify.securityservice.repository.AppRoleRepository;
import Sportify.securityservice.repository.AppUserRepository;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) throws NullPointerException {
        AppUser user= appUserRepository.findByUsername(appUser.getUsername());
        if(user != null) throw new NullPointerException("Username already used !" );
        String pw=appUser.getPassword();
        appUser.setPassword(passwordEncoder.encode(pw));
        return appUserRepository.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppRole loadRoleByRoleName(String roleName) {
        return appRoleRepository.findByRoleName(roleName);
    }

    @Override
    public List<AppUser> lisUsers() {
        return appUserRepository.findAll();
    }

    @Override
    public List<AppRole> lisRoles() {
        return appRoleRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        appUserRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found with this ID: " + id));
        appUserRepository.deleteById(id);
    }
}