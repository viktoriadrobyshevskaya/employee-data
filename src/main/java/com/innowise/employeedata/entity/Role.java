package com.innowise.employeedata.entity;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Role {
    ROLE_ADMIN,
    @JsonEnumDefaultValue
    ROLE_USER

}
