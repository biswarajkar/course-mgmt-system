/**
 * 
 */
package com.jga.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.jga.entity.HorizontalAlignment;
import com.jga.entity.Role;
import com.jga.entity.UserAction;
import com.jga.entity.VerticalAlignment;
import com.jga.repository.HorizontalAlignmentRepository;
import com.jga.repository.RoleRepository;
import com.jga.repository.UserActionRepository;
import com.jga.repository.VerticalAlignmentRepository;

/**
 * @author dey
 *
 */
public class EnumService implements IEnumService {
	@Autowired
	private UserActionRepository userActionRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private HorizontalAlignmentRepository hAlignRepository;
	
	@Autowired
	private VerticalAlignmentRepository vAlignRepository;
	
	
	/* (non-Javadoc)
	 * @see com.jga.service.IEnumService#getUserActions()
	 */
	@Override
	public Collection<UserAction> getUserActions() {
		final Collection<UserAction> userActions = new ArrayList<>();
		userActionRepository.findAll()
							.iterator()
							.forEachRemaining(userActions::add);

		return userActions;
	}

	/* (non-Javadoc)
	 * @see com.jga.service.IEnumService#getRoles()
	 */
	@Override
	public Collection<Role> getRoles() {
		final Collection<Role> roles = new ArrayList<>();
		roleRepository.findAll()
					  .iterator()
					  .forEachRemaining(roles::add);

		return roles;

	}

	/* (non-Javadoc)
	 * @see com.jga.service.IEnumService#getHorizontalAlignments()
	 */
	@Override
	public Collection<HorizontalAlignment> getHorizontalAlignments() {
		final Collection<HorizontalAlignment> hAligns = new ArrayList<>();
		hAlignRepository.findAll()
						.iterator()
						.forEachRemaining(hAligns::add);

		return hAligns;

	}

	/* (non-Javadoc)
	 * @see com.jga.service.IEnumService#getVerticalAlignments()
	 */
	@Override
	public Collection<VerticalAlignment> getVerticalAlignments() {
		final Collection<VerticalAlignment> vAligns = new ArrayList<>();
		vAlignRepository.findAll()
						.iterator()
						.forEachRemaining(vAligns::add);

		return vAligns;
	}

}
