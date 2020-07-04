package com.tenacious.forms.service.impl;

import com.tenacious.forms.service.UserResponseService;
import com.tenacious.forms.domain.UserResponse;
import com.tenacious.forms.repository.UserResponseRepository;
import com.tenacious.forms.service.dto.UserResponseDTO;
import com.tenacious.forms.service.mapper.UserResponseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UserResponse}.
 */
@Service
@Transactional
public class UserResponseServiceImpl implements UserResponseService {

    private final Logger log = LoggerFactory.getLogger(UserResponseServiceImpl.class);

    private final UserResponseRepository userResponseRepository;

    private final UserResponseMapper userResponseMapper;

    public UserResponseServiceImpl(UserResponseRepository userResponseRepository, UserResponseMapper userResponseMapper) {
        this.userResponseRepository = userResponseRepository;
        this.userResponseMapper = userResponseMapper;
    }

    @Override
    public UserResponseDTO save(UserResponseDTO userResponseDTO) {
        log.debug("Request to save UserResponse : {}", userResponseDTO);
        UserResponse userResponse = userResponseMapper.toEntity(userResponseDTO);
        userResponse = userResponseRepository.save(userResponse);
        return userResponseMapper.toDto(userResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserResponses");
        return userResponseRepository.findAll(pageable)
            .map(userResponseMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDTO> findOne(Long id) {
        log.debug("Request to get UserResponse : {}", id);
        return userResponseRepository.findById(id)
            .map(userResponseMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserResponse : {}", id);
        userResponseRepository.deleteById(id);
    }
}
