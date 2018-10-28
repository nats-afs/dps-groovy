package com.asforsoft.dps.model

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name='acl_object_identity')
@ToString
class ObjectIdentity {
  @ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name='parent_object')
  ObjectIdentity parentObject

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name='object_id_class')
  DomainObject objectIdClass

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = 'owner_sid')
  SecurityIdentity ownerSid

  boolean entriesInheriting
}
