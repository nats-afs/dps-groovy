package com.asforsoft.dps.repository

import com.asforsoft.dps.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername (String username)
}
