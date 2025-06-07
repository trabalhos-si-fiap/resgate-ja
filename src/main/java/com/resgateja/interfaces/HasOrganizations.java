package com.resgateja.interfaces;

import com.resgateja.dtos.CreateOrganization;

import java.util.List;

public interface HasOrganizations {
    List<CreateOrganization> organizations();
}
