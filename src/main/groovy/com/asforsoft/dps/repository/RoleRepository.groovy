package com.asforsoft.dps.repository

import com.asforsoft.dps.model.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface RoleRepository extends JpaRepository<Role, Long>{

}
