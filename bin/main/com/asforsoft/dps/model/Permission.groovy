package com.asforsoft.dps.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name='acl_entry')
@ToString
class Permission {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id
  boolean granting
  boolean auditSuccess
  boolean auditFailure
}
