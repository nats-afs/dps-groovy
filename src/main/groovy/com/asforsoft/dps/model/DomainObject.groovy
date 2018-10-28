package com.asforsoft.dps.model

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = 'acl_class')
@ToString
class DomainObject {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id
  @Column(name = 'class')
  String domainObject
}
