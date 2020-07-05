package com.tenacious.forms.web.rest;

import com.tenacious.forms.service.SurveyStatsService;
import com.tenacious.forms.web.rest.errors.BadRequestAlertException;
import com.tenacious.forms.service.dto.SurveyStatsDTO;

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
 * REST controller for managing {@link com.tenacious.forms.domain.SurveyStats}.
 */
@RestController
@RequestMapping("/api")
public class SurveyStatsResource {

    private final Logger log = LoggerFactory.getLogger(SurveyStatsResource.class);

    private static final String ENTITY_NAME = "surveyStats";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SurveyStatsService surveyStatsService;

    public SurveyStatsResource(SurveyStatsService surveyStatsService) {
        this.surveyStatsService = surveyStatsService;
    }

    /**
     * {@code POST  /survey-stats} : Create a new surveyStats.
     *
     * @param surveyStatsDTO the surveyStatsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new surveyStatsDTO, or with status {@code 400 (Bad Request)} if the surveyStats has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/survey-stats")
    public ResponseEntity<SurveyStatsDTO> createSurveyStats(@RequestBody SurveyStatsDTO surveyStatsDTO) throws URISyntaxException {
        log.debug("REST request to save SurveyStats : {}", surveyStatsDTO);
        if (surveyStatsDTO.getId() != null) {
            throw new BadRequestAlertException("A new surveyStats cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SurveyStatsDTO result = surveyStatsService.save(surveyStatsDTO);
        return ResponseEntity.created(new URI("/api/survey-stats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /survey-stats} : Updates an existing surveyStats.
     *
     * @param surveyStatsDTO the surveyStatsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated surveyStatsDTO,
     * or with status {@code 400 (Bad Request)} if the surveyStatsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the surveyStatsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/survey-stats")
    public ResponseEntity<SurveyStatsDTO> updateSurveyStats(@RequestBody SurveyStatsDTO surveyStatsDTO) throws URISyntaxException {
        log.debug("REST request to update SurveyStats : {}", surveyStatsDTO);
        if (surveyStatsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SurveyStatsDTO result = surveyStatsService.save(surveyStatsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, surveyStatsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /survey-stats} : get all the surveyStats.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of surveyStats in body.
     */
    @GetMapping("/survey-stats")
    public ResponseEntity<List<SurveyStatsDTO>> getAllSurveyStats(Pageable pageable) {
        log.debug("REST request to get a page of SurveyStats");
        Page<SurveyStatsDTO> page = surveyStatsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /survey-stats/:id} : get the "id" surveyStats.
     *
     * @param id the id of the surveyStatsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the surveyStatsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/survey-stats/{id}")
    public ResponseEntity<SurveyStatsDTO> getSurveyStats(@PathVariable Long id) {
        log.debug("REST request to get SurveyStats : {}", id);
        Optional<SurveyStatsDTO> surveyStatsDTO = surveyStatsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(surveyStatsDTO);
    }

    /**
     * {@code DELETE  /survey-stats/:id} : delete the "id" surveyStats.
     *
     * @param id the id of the surveyStatsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/survey-stats/{id}")
    public ResponseEntity<Void> deleteSurveyStats(@PathVariable Long id) {
        log.debug("REST request to delete SurveyStats : {}", id);
        surveyStatsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
