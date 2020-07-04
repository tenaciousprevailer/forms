package com.tenacious.forms.web.rest;

import com.tenacious.forms.domain.UserResponse;
import com.tenacious.forms.repository.UserResponseRepository;
import com.tenacious.forms.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.tenacious.forms.domain.UserResponse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserResponseResource {

    private final Logger log = LoggerFactory.getLogger(UserResponseResource.class);

    private static final String ENTITY_NAME = "userResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserResponseRepository userResponseRepository;

    public UserResponseResource(UserResponseRepository userResponseRepository) {
        this.userResponseRepository = userResponseRepository;
    }

    /**
     * {@code POST  /user-responses} : Create a new userResponse.
     *
     * @param userResponse the userResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userResponse, or with status {@code 400 (Bad Request)} if the userResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-responses")
    public ResponseEntity<UserResponse> createUserResponse(@RequestBody UserResponse userResponse) throws URISyntaxException {
        log.debug("REST request to save UserResponse : {}", userResponse);
        if (userResponse.getId() != null) {
            throw new BadRequestAlertException("A new userResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserResponse result = userResponseRepository.save(userResponse);
        return ResponseEntity.created(new URI("/api/user-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-responses} : Updates an existing userResponse.
     *
     * @param userResponse the userResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userResponse,
     * or with status {@code 400 (Bad Request)} if the userResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-responses")
    public ResponseEntity<UserResponse> updateUserResponse(@RequestBody UserResponse userResponse) throws URISyntaxException {
        log.debug("REST request to update UserResponse : {}", userResponse);
        if (userResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserResponse result = userResponseRepository.save(userResponse);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userResponse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-responses} : get all the userResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userResponses in body.
     */
    @GetMapping("/user-responses")
    public List<UserResponse> getAllUserResponses() {
        log.debug("REST request to get all UserResponses");
        return userResponseRepository.findAll();
    }

    /**
     * {@code GET  /user-responses/:id} : get the "id" userResponse.
     *
     * @param id the id of the userResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-responses/{id}")
    public ResponseEntity<UserResponse> getUserResponse(@PathVariable Long id) {
        log.debug("REST request to get UserResponse : {}", id);
        Optional<UserResponse> userResponse = userResponseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(userResponse);
    }

    /**
     * {@code DELETE  /user-responses/:id} : delete the "id" userResponse.
     *
     * @param id the id of the userResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-responses/{id}")
    public ResponseEntity<Void> deleteUserResponse(@PathVariable Long id) {
        log.debug("REST request to delete UserResponse : {}", id);
        userResponseRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
