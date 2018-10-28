package com.asforsoft.dps.model

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = "authorities", uniqueConstraints= @UniqueConstraint(columnNames= ["user_id", "authority"]))
@ToString
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id
    String authority
}
