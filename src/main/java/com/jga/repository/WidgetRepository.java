package com.jga.repository;

import javax.transaction.Transactional;

import com.jga.entity.Widget;

/**
 * @author Biswaraj
 *
 */
@Transactional
public interface WidgetRepository extends WidgetBaseRepository<Widget> { }
