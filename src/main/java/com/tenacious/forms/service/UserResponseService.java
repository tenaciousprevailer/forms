package com.tenacious.forms.service;

import com.tenacious.forms.service.dto.UserResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.tenacious.forms.domain.UserResponse}.
 */
public interface UserResponseService {

    /**
     * Save a userResponse.
     *
     * @param userResponseDTO the entity to save.
     * @return the persisted entity.
     */
    UserResponseDTO save(UserResponseDTO userResponseDTO);

    /**
     * Get all the userResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserResponseDTO> findAll(Pageable pageable);


    /**
     * Get the "id" userResponse.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserResponseDTO> findOne(Long id);

    /**
     * Delete the "id" userResponse.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
