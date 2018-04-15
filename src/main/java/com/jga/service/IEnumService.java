/**
 * 
 */
package com.jga.service;

import java.util.Collection;

import com.jga.entity.HorizontalAlignment;
import com.jga.entity.Role;
import com.jga.entity.UserAction;
import com.jga.entity.VerticalAlignment;

/**
 * @author dey
 *
 */
public interface IEnumService {
	public Collection<UserAction> getUserActions();

	public Collection<Role> getRoles();

	public Collection<HorizontalAlignment> getHorizontalAlignments();

	public Collection<VerticalAlignment> getVerticalAlignments();
}
