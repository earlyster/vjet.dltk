/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.core.mixin;

import org.eclipse.dltk.mod.core.ISourceModule;

public interface IMixinElement {
	/**
	 * Return all children elements.
	 * If not builded then builded.
	 * @return children elements.
	 */
	IMixinElement[] getChildren();
	
	/**
	 * Add name to current key and return child.
	 * @param name
	 * @return
	 */
	IMixinElement getChildren( String name );
	
	/**
	 * Return key value.
	 * @return key value
	 */
	String getKey();
	
	/**
	 * Return last key segment.
	 * @return
	 */
	String getLastKeySegment();
	
	/**
	 * Return sepecified objects.
	 * If not builded then builded.
	 * @param module
	 * @return
	 */
	Object[] getObjects(ISourceModule module);
	
	/**
	 * Returns all not equal objects.
	 * If not builded then builded.
	 * @return
	 */
	Object[] getAllObjects();
	
	/**
	 * Return parent mixin. Parent mixin aren't builded. 
	 * @return
	 */
	IMixinElement getParent();
	
	/**
	 * If not builded then builded.
	 * @return
	 */
	ISourceModule[] getSourceModules();
}
