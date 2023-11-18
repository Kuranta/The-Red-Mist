package com.springproject.service;

import com.springproject.models.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles();
    public Role findRole(String name);

}
