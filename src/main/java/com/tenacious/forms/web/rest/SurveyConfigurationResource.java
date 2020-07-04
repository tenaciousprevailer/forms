package com.tenacious.forms.web.rest;

import com.tenacious.forms.domain.SurveyConfiguration;
import com.tenacious.forms.repository.SurveyConfigurationRepository;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.tenacious.forms.domain.SurveyConfiguration}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SurveyConfigurationResource {

    private final Logger log = LoggerFactory.getLogger(SurveyConfigurationResource.class);

    private static final String ENTITY_NAME = "surveyConfiguration";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveyConfigurationRepository surveyConfigurationRepository;

    public SurveyConfigurationResource(SurveyConfigurationRepository surveyConfigurationRepository) {
        this.surveyConfigurationRepository = surveyConfigurationRepository;
    }

    /**
     * {@code POST  /survey-configurations} : Create a new surveyConfiguration.
     *
     * @param surveyConfiguration the surveyConfiguration to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveyConfiguration, or with status {@code 400 (Bad Request)} if the surveyConfiguration has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/survey-configurations")
    public ResponseEntity<SurveyConfiguration> createSurveyConfiguration(@RequestBody SurveyConfiguration surveyConfiguration) throws URISyntaxException {
        log.debug("REST request to save SurveyConfiguration : {}", surveyConfiguration);
        if (surveyConfiguration.getId() != null) {
            throw new BadRequestAlertException("A new surveyConfiguration cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyConfiguration result = surveyConfigurationRepository.save(surveyConfiguration);
        return ResponseEntity.created(new URI("/api/survey-configurations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /survey-configurations} : Updates an existing surveyConfiguration.
     *
     * @param surveyConfiguration the surveyConfiguration to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveyConfiguration,
     * or with status {@code 400 (Bad Request)} if the surveyConfiguration is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveyConfiguration couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/survey-configurations")
    public ResponseEntity<SurveyConfiguration> updateSurveyConfiguration(@RequestBody SurveyConfiguration surveyConfiguration) throws URISyntaxException {
        log.debug("REST request to update SurveyConfiguration : {}", surveyConfiguration);
        if (surveyConfiguration.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurveyConfiguration result = surveyConfigurationRepository.save(surveyConfiguration);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, surveyConfiguration.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /survey-configurations} : get all the surveyConfigurations.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveyConfigurations in body.
     */
    @GetMapping("/survey-configurations")
    public List<SurveyConfiguration> getAllSurveyConfigurations(@RequestParam(required = false) String filter) {
        if ("survey-is-null".equals(filter)) {
            log.debug("REST request to get all SurveyConfigurations where survey is null");
            return StreamSupport
                .stream(surveyConfigurationRepository.findAll().spliterator(), false)
                .filter(surveyConfiguration -> surveyConfiguration.getSurvey() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all SurveyConfigurations");
        return surveyConfigurationRepository.findAll();
    }

    /**
     * {@code GET  /survey-configurations/:id} : get the "id" surveyConfiguration.
     *
     * @param id the id of the surveyConfiguration to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveyConfiguration, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/survey-configurations/{id}")
    public ResponseEntity<SurveyConfiguration> getSurveyConfiguration(@PathVariable Long id) {
        log.debug("REST request to get SurveyConfiguration : {}", id);
        Optional<SurveyConfiguration> surveyConfiguration = surveyConfigurationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(surveyConfiguration);
    }

    /**
     * {@code DELETE  /survey-configurations/:id} : delete the "id" surveyConfiguration.
     *
     * @param id the id of the surveyConfiguration to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/survey-configurations/{id}")
    public ResponseEntity<Void> deleteSurveyConfiguration(@PathVariable Long id) {
        log.debug("REST request to delete SurveyConfiguration : {}", id);
        surveyConfigurationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
