package com.asforsoft.dps.model

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'acl_sid')
@ToString
class SecurityIdentity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id
  boolean principal
  String sid
}
