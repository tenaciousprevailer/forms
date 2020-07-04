package com.tenacious.forms.web.rest;

import com.tenacious.forms.domain.SurveyStats;
import com.tenacious.forms.repository.SurveyStatsRepository;
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
 * REST controller for managing {@link com.tenacious.forms.domain.SurveyStats}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SurveyStatsResource {

    private final Logger log = LoggerFactory.getLogger(SurveyStatsResource.class);

    private static final String ENTITY_NAME = "surveyStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveyStatsRepository surveyStatsRepository;

    public SurveyStatsResource(SurveyStatsRepository surveyStatsRepository) {
        this.surveyStatsRepository = surveyStatsRepository;
    }

    /**
     * {@code POST  /survey-stats} : Create a new surveyStats.
     *
     * @param surveyStats the surveyStats to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveyStats, or with status {@code 400 (Bad Request)} if the surveyStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/survey-stats")
    public ResponseEntity<SurveyStats> createSurveyStats(@RequestBody SurveyStats surveyStats) throws URISyntaxException {
        log.debug("REST request to save SurveyStats : {}", surveyStats);
        if (surveyStats.getId() != null) {
            throw new BadRequestAlertException("A new surveyStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyStats result = surveyStatsRepository.save(surveyStats);
        return ResponseEntity.created(new URI("/api/survey-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /survey-stats} : Updates an existing surveyStats.
     *
     * @param surveyStats the surveyStats to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveyStats,
     * or with status {@code 400 (Bad Request)} if the surveyStats is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveyStats couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/survey-stats")
    public ResponseEntity<SurveyStats> updateSurveyStats(@RequestBody SurveyStats surveyStats) throws URISyntaxException {
        log.debug("REST request to update SurveyStats : {}", surveyStats);
        if (surveyStats.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurveyStats result = surveyStatsRepository.save(surveyStats);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, surveyStats.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /survey-stats} : get all the surveyStats.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveyStats in body.
     */
    @GetMapping("/survey-stats")
    public List<SurveyStats> getAllSurveyStats(@RequestParam(required = false) String filter) {
        if ("survey-is-null".equals(filter)) {
            log.debug("REST request to get all SurveyStatss where survey is null");
            return StreamSupport
                .stream(surveyStatsRepository.findAll().spliterator(), false)
                .filter(surveyStats -> surveyStats.getSurvey() == null)
                .collect(Collectors.toList());
        }
        log.debug("REST request to get all SurveyStats");
        return surveyStatsRepository.findAll();
    }

    /**
     * {@code GET  /survey-stats/:id} : get the "id" surveyStats.
     *
     * @param id the id of the surveyStats to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveyStats, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/survey-stats/{id}")
    public ResponseEntity<SurveyStats> getSurveyStats(@PathVariable Long id) {
        log.debug("REST request to get SurveyStats : {}", id);
        Optional<SurveyStats> surveyStats = surveyStatsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(surveyStats);
    }

    /**
     * {@code DELETE  /survey-stats/:id} : delete the "id" surveyStats.
     *
     * @param id the id of the surveyStats to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/survey-stats/{id}")
    public ResponseEntity<Void> deleteSurveyStats(@PathVariable Long id) {
        log.debug("REST request to delete SurveyStats : {}", id);
        surveyStatsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
