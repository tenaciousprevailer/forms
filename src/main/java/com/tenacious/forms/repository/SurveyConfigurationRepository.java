package com.tenacious.forms.repository;

import com.tenacious.forms.domain.SurveyConfiguration;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SurveyConfiguration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyConfigurationRepository extends JpaRepository<SurveyConfiguration, Long> {
}
