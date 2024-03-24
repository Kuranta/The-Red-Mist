package com.springproject.service;

import com.springproject.models.Role;
import com.springproject.repository.RoleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {
    @InjectMocks
    private RoleServiceImpl roleService;
    @Mock
    private RoleRepository roleRepository;

    @Test
    void getRoles_shouldCallRoleRepository_returnRoleList() {
        //given
        List<Role> expectedRoles = List.of(new Role(2l,"USER"));
        //when
        Mockito.when(roleRepository.findAll()).thenReturn(expectedRoles);
        List<Role> actualRoles = roleService.getRoles();
        //then
        Assertions.assertEquals(actualRoles,expectedRoles);
        Assertions.assertEquals(1, actualRoles.size());
    }

    @Test
    void findRole_shouldCallRoleRepository_returnRoleByName() {
        //given
        String roleName = "USER";
        Role expectedRole = new Role(2l,"USER");
        //when
        Mockito.when(roleRepository.findByName(roleName)).thenReturn(Optional.ofNullable(expectedRole));
        Role actualRole = roleService.findRole(roleName);
        //then
        Assertions.assertEquals(actualRole,expectedRole);
    }

    @Test
    void findRole_shouldThrowNoSuchElementException_roleWithSuchNameNotExist(){
        //given
        String roleName = "JOKERGE";
        //when
        Mockito.when(roleRepository.findByName(roleName)).thenThrow(new NoSuchElementException("Role with such name is not exist: " + roleName));
        //then
        Assertions.assertThrows(NoSuchElementException.class, () -> roleService.findRole(roleName));
    }
}