package com.jga.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jga.entity.Widget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface WidgetRepository extends WidgetBaseRepository<Widget> {
	@Query(value="DELETE w FROM Widget AS w WHERE w.widgetId = :widgetId", nativeQuery=true)
	@Modifying
	void deleteByWidgetId(@Param("widgetId") int widgetId);
}
