package Sportify.securityservice.service;


import Sportify.securityservice.entities.AppRole;
import Sportify.securityservice.entities.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser) throws Exception;
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    AppRole loadRoleByRoleName(String roleName);
    List<AppUser> lisUsers();
    List<AppRole> lisRoles();
    void deleteUser(long id);

}
