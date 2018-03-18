package com.jga.repository;

import javax.transaction.Transactional;
import com.jga.entity.ImageWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface ImageWidgetRepository extends WidgetBaseRepository<ImageWidget> {
}
