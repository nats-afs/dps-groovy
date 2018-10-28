package com.asforsoft.dps.security.auth.jwt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

abstract class SimpleGrantedAuthorityMixin {
    @JsonCreator
    SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {}
}
