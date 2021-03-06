/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *

 *******************************************************************************/
package org.eclipse.dltk.mod.internal.core;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.mod.core.IMethod;
import org.eclipse.dltk.mod.core.IModelElement;
import org.eclipse.dltk.mod.core.IScriptFolder;
import org.eclipse.dltk.mod.core.ModelException;
import org.eclipse.dltk.mod.utils.CorePrinter;

public class SourceMethod extends NamedMember implements IMethod {

	public SourceMethod(ModelElement parent, String name) {
		super(parent, name);
	}

	public int getElementType() {
		return METHOD;
	}

	public boolean equals(Object o) {
		if (!(o instanceof SourceMethod)) {
			return false;
		}
		return super.equals(o);
	}

	public String[] getParameters() throws ModelException {
		SourceMethodElementInfo info = (SourceMethodElementInfo) this
				.getElementInfo();
		if (info != null) {
			return info.getArgumentNames();
		}
		return null;
	}

	public String[] getParameterInitializers() throws ModelException {
		SourceMethodElementInfo info = (SourceMethodElementInfo) this
				.getElementInfo();
		if (info != null) {
			return info.getArgumentInitializers();
		}
		return null;
	}

	public void printNode(CorePrinter output) {
		output.formatPrint("DLTK Source Method:" + getElementName()); //$NON-NLS-1$
		output.indent();
		try {
			IModelElement modelElements[] = this.getChildren();
			for (int i = 0; i < modelElements.length; ++i) {
				IModelElement element = modelElements[i];
				if (element instanceof ModelElement) {
					((ModelElement) element).printNode(output);
				} else {
					output.print("Unknown element:" + element); //$NON-NLS-1$
				}
			}
		} catch (ModelException ex) {
			output.formatPrint(ex.getLocalizedMessage());
		}
		output.dedent();
	}

	/**
	 * @see IMethod
	 */
	public boolean isConstructor() throws ModelException {
		SourceMethodElementInfo info = (SourceMethodElementInfo) getElementInfo();
		return info.isConstructor();
	}

	protected char getHandleMementoDelimiter() {
		return JEM_METHOD;
	}

	public String getFullyQualifiedName(String enclosingTypeSeparator) {
		try {
			return getFullyQualifiedName(enclosingTypeSeparator, false);
		} catch (ModelException e) {
			// exception thrown only when showing parameters
			return null;
		}
	}

	public String getFullyQualifiedName() {
		return getFullyQualifiedName("$"); //$NON-NLS-1$
	}

	public IScriptFolder getScriptFolder() {
		IModelElement parentElement = this.parent;
		while (parentElement != null) {
			if (parentElement.getElementType() == IModelElement.SCRIPT_FOLDER) {
				return (IScriptFolder) parentElement;
			} else {
				parentElement = parentElement.getParent();
			}
		}
		Assert.isTrue(false); // should not happen
		return null;
	}
}
