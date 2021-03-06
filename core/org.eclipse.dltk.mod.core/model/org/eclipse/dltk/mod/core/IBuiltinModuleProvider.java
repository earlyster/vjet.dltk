/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.core;

/**
 * Used to provide builtin information into model.
 * 
 * @author Haiodo
 */
public interface IBuiltinModuleProvider {
	/**
	 * Used to builtin model contributions.
	 * 
	 * @return
	 */
	String[] getBuiltinModules();

	String getBuiltinModuleContent(String name);

	/**
	 * Returns the time that the content denoted by this provider was last
	 * modified.
	 * 
	 * @return
	 */
	long lastModified();
}
