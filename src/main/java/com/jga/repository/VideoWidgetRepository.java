package com.jga.repository;

import javax.transaction.Transactional;
import com.jga.entity.VideoWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface VideoWidgetRepository extends WidgetBaseRepository<VideoWidget> {
}
