package com.springproject.dao;

import com.springproject.models.Role;

import java.util.List;

public interface RoleDAO {

    public List<Role> getRoles();

    public Role findRole(String name);
}
