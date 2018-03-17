package com.jga.repository;

import javax.transaction.Transactional;
import com.jga.entity.HtmlWidget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface HtmlWidgetRepository extends WidgetBaseRepository<HtmlWidget> { }
