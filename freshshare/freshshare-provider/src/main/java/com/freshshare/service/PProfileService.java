package com.freshshare.service;

import com.freshshare.controller.Dto.ProfileDto;

import java.util.List;
import java.util.Map;

public interface PProfileService {
    List<Map<String,Object>> selectProfile(ProfileDto profileDto);
}
