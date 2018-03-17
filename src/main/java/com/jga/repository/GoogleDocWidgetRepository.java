package com.jga.repository;

import javax.transaction.Transactional;
import com.jga.entity.GoogleDocWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface GoogleDocWidgetRepository extends WidgetBaseRepository<GoogleDocWidget> {
}
