package com.do_an.clinic.services;

import com.do_an.clinic.exceptions.DataNotFoundException;

import java.util.List;

public interface IProfileDetailService {

    public String createProfileDetails(Long profileId, List<Long> serviceIds) throws DataNotFoundException;
}
