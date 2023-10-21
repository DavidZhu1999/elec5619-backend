package com.freshshare.service.impl;

import com.freshshare.controller.Dto.ProfileDto;
import com.freshshare.mapper.PProfileMapper;
import com.freshshare.service.PProfileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PProfileServiceImpl implements PProfileService {
    @Resource
    private PProfileMapper PProfileMapper;
    @Override
    public List<Map<String, Object>> selectProfile(ProfileDto profileDto) {
        return PProfileMapper.selectProfile(profileDto);
    }
}
