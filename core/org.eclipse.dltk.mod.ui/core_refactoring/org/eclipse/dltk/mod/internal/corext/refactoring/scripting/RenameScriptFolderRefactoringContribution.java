/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.internal.corext.refactoring.scripting;

import org.eclipse.dltk.mod.internal.corext.refactoring.ScriptRefactoringContribution;
import org.eclipse.dltk.mod.internal.corext.refactoring.rename.RenameScriptFolderProcessor;
import org.eclipse.dltk.mod.internal.corext.refactoring.rename.ScriptRenameRefactoring;
import org.eclipse.ltk.core.refactoring.Refactoring;
import org.eclipse.ltk.core.refactoring.RefactoringDescriptor;


/**
 * Refactoring contribution for the rename package refactoring.
 * 
	 *
 */
public final class RenameScriptFolderRefactoringContribution extends ScriptRefactoringContribution {

	/**
	 * {@inheritDoc}
	 */
	public Refactoring createRefactoring(final RefactoringDescriptor descriptor) {
		return new ScriptRenameRefactoring(new RenameScriptFolderProcessor(null));
	}
}
