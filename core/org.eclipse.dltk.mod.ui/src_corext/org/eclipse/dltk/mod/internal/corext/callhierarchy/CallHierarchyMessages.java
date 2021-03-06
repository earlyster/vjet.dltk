/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.internal.corext.callhierarchy;

import org.eclipse.osgi.util.NLS;

public final class CallHierarchyMessages extends NLS {

	private static final String BUNDLE_NAME= "org.eclipse.dltk.mod.internal.corext.callhierarchy.CallHierarchyMessages";//$NON-NLS-1$

	private CallHierarchyMessages() {
		// Do not instantiate
	}

	public static String CallerMethodWrapper_taskname;
	public static String CalleeMethodWrapper_taskname;

	static {
		NLS.initializeMessages(BUNDLE_NAME, CallHierarchyMessages.class);
	}
}
