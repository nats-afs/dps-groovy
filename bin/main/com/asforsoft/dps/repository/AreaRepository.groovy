package com.asforsoft.dps.repository

import com.asforsoft.dps.model.Area
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.stereotype.Repository

@RepositoryRestResource
//@RepositoryRestResource(collectionResourceRel = "claimants", path = "claimants")
interface AreaRepository extends JpaRepository<Area,Long>{

//    @RestResource(path = "by-name")
//    List<Claimant> findAllByName(@Param("name") String name);
//
//    @RestResource(path = "by-name-contain")
//    List<Claimant> findAllByNameContainingIgnoreCase(@Param("name") String name);

}