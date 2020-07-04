package com.tenacious.forms.web.rest;

import com.tenacious.forms.service.UserResponseService;
import com.tenacious.forms.web.rest.errors.BadRequestAlertException;
import com.tenacious.forms.service.dto.UserResponseDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
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
public class UserResponseResource {

    private final Logger log = LoggerFactory.getLogger(UserResponseResource.class);

    private static final String ENTITY_NAME = "userResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserResponseService userResponseService;

    public UserResponseResource(UserResponseService userResponseService) {
        this.userResponseService = userResponseService;
    }

    /**
     * {@code POST  /user-responses} : Create a new userResponse.
     *
     * @param userResponseDTO the userResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userResponseDTO, or with status {@code 400 (Bad Request)} if the userResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-responses")
    public ResponseEntity<UserResponseDTO> createUserResponse(@RequestBody UserResponseDTO userResponseDTO) throws URISyntaxException {
        log.debug("REST request to save UserResponse : {}", userResponseDTO);
        if (userResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new userResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserResponseDTO result = userResponseService.save(userResponseDTO);
        return ResponseEntity.created(new URI("/api/user-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-responses} : Updates an existing userResponse.
     *
     * @param userResponseDTO the userResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userResponseDTO,
     * or with status {@code 400 (Bad Request)} if the userResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-responses")
    public ResponseEntity<UserResponseDTO> updateUserResponse(@RequestBody UserResponseDTO userResponseDTO) throws URISyntaxException {
        log.debug("REST request to update UserResponse : {}", userResponseDTO);
        if (userResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        UserResponseDTO result = userResponseService.save(userResponseDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /user-responses} : get all the userResponses.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userResponses in body.
     */
    @GetMapping("/user-responses")
    public ResponseEntity<List<UserResponseDTO>> getAllUserResponses(Pageable pageable) {
        log.debug("REST request to get a page of UserResponses");
        Page<UserResponseDTO> page = userResponseService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /user-responses/:id} : get the "id" userResponse.
     *
     * @param id the id of the userResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-responses/{id}")
    public ResponseEntity<UserResponseDTO> getUserResponse(@PathVariable Long id) {
        log.debug("REST request to get UserResponse : {}", id);
        Optional<UserResponseDTO> userResponseDTO = userResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userResponseDTO);
    }

    /**
     * {@code DELETE  /user-responses/:id} : delete the "id" userResponse.
     *
     * @param id the id of the userResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-responses/{id}")
    public ResponseEntity<Void> deleteUserResponse(@PathVariable Long id) {
        log.debug("REST request to delete UserResponse : {}", id);
        userResponseService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
