package com.tenacious.forms.web.rest;

import com.tenacious.forms.service.SurveyConfigurationService;
import com.tenacious.forms.web.rest.errors.BadRequestAlertException;
import com.tenacious.forms.service.dto.SurveyConfigurationDTO;

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
 * REST controller for managing {@link com.tenacious.forms.domain.SurveyConfiguration}.
 */
@RestController
@RequestMapping("/api")
public class SurveyConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(SurveyConfigurationResource.class);

    private static final String ENTITY_NAME = "surveyConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveyConfigurationService surveyConfigurationService;

    public SurveyConfigurationResource(SurveyConfigurationService surveyConfigurationService) {
        this.surveyConfigurationService = surveyConfigurationService;
    }

    /**
     * {@code POST  /survey-configurations} : Create a new surveyConfiguration.
     *
     * @param surveyConfigurationDTO the surveyConfigurationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveyConfigurationDTO, or with status {@code 400 (Bad Request)} if the surveyConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/survey-configurations")
    public ResponseEntity<SurveyConfigurationDTO> createSurveyConfiguration(@RequestBody SurveyConfigurationDTO surveyConfigurationDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyConfiguration : {}", surveyConfigurationDTO);
        if (surveyConfigurationDTO.getId() != null) {
            throw new BadRequestAlertException("A new surveyConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyConfigurationDTO result = surveyConfigurationService.save(surveyConfigurationDTO);
        return ResponseEntity.created(new URI("/api/survey-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /survey-configurations} : Updates an existing surveyConfiguration.
     *
     * @param surveyConfigurationDTO the surveyConfigurationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveyConfigurationDTO,
     * or with status {@code 400 (Bad Request)} if the surveyConfigurationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveyConfigurationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/survey-configurations")
    public ResponseEntity<SurveyConfigurationDTO> updateSurveyConfiguration(@RequestBody SurveyConfigurationDTO surveyConfigurationDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyConfiguration : {}", surveyConfigurationDTO);
        if (surveyConfigurationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurveyConfigurationDTO result = surveyConfigurationService.save(surveyConfigurationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, surveyConfigurationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /survey-configurations} : get all the surveyConfigurations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveyConfigurations in body.
     */
    @GetMapping("/survey-configurations")
    public ResponseEntity<List<SurveyConfigurationDTO>> getAllSurveyConfigurations(Pageable pageable) {
        log.debug("REST request to get a page of SurveyConfigurations");
        Page<SurveyConfigurationDTO> page = surveyConfigurationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /survey-configurations/:id} : get the "id" surveyConfiguration.
     *
     * @param id the id of the surveyConfigurationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveyConfigurationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/survey-configurations/{id}")
    public ResponseEntity<SurveyConfigurationDTO> getSurveyConfiguration(@PathVariable Long id) {
        log.debug("REST request to get SurveyConfiguration : {}", id);
        Optional<SurveyConfigurationDTO> surveyConfigurationDTO = surveyConfigurationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(surveyConfigurationDTO);
    }

    /**
     * {@code DELETE  /survey-configurations/:id} : delete the "id" surveyConfiguration.
     *
     * @param id the id of the surveyConfigurationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/survey-configurations/{id}")
    public ResponseEntity<Void> deleteSurveyConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete SurveyConfiguration : {}", id);
        surveyConfigurationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
