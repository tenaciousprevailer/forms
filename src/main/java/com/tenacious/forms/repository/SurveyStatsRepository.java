package com.tenacious.forms.repository;

import com.tenacious.forms.domain.SurveyStats;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SurveyStats entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurveyStatsRepository extends JpaRepository<SurveyStats, Long> {
}
