/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.ui.actions;

import org.eclipse.dltk.mod.core.DLTKCore;
import org.eclipse.dltk.mod.core.IField;
import org.eclipse.dltk.mod.core.IMethod;
import org.eclipse.dltk.mod.core.IModelElement;
import org.eclipse.dltk.mod.core.IPackageDeclaration;
import org.eclipse.dltk.mod.core.IScriptFolder;
import org.eclipse.dltk.mod.core.ISourceModule;
import org.eclipse.dltk.mod.core.IType;
import org.eclipse.dltk.mod.core.ModelException;
import org.eclipse.dltk.mod.core.search.IDLTKSearchConstants;
import org.eclipse.dltk.mod.core.search.IDLTKSearchScope;
import org.eclipse.dltk.mod.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.mod.internal.ui.search.DLTKSearchScopeFactory;
import org.eclipse.dltk.mod.internal.ui.search.SearchMessages;
import org.eclipse.dltk.mod.ui.DLTKPluginImages;
import org.eclipse.dltk.mod.ui.search.ElementQuerySpecification;
import org.eclipse.dltk.mod.ui.search.QuerySpecification;
import org.eclipse.ui.IWorkbenchSite;


/**
 * Finds references of the selected element in the workspace. The action is
 * applicable to selections representing a Script element.
 * 
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * 
	 *
 */
public abstract class FindReferencesAction extends FindAction {

	/**
	 * Creates a new <code>FindReferencesAction</code>. The action requires
	 * that the selection provided by the site's selection provider is of type
	 * <code>org.eclipse.jface.viewers.IStructuredSelection</code>.
	 * 
	 * @param site
	 *            the site providing context information for this action
	 */
	public FindReferencesAction(IWorkbenchSite site) {
		super(site);
	}

	/**
	 * Note: This constructor is for internal use only. Clients should not call
	 * this constructor.
	 * 
	 * @param editor
	 *            the Script editor
	 */
	public FindReferencesAction(ScriptEditor editor) {
		super(editor);
	}

	Class[] getValidTypes() {
		return new Class[] { ISourceModule.class, IType.class, IMethod.class, IField.class, IPackageDeclaration.class, 
				IScriptFolder.class};
	}

	void init() {
		setText(SearchMessages.Search_FindReferencesAction_label);
		setToolTipText(SearchMessages.Search_FindReferencesAction_tooltip);
		setImageDescriptor(DLTKPluginImages.DESC_OBJS_SEARCH_REF);
//		PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IJavaHelpContextIds.FIND_REFERENCES_IN_WORKSPACE_ACTION);
		if (DLTKCore.DEBUG) {
			System.out.println("TODO: Add help support here..."); //$NON-NLS-1$
		}
	}

	int getLimitTo() {
		return IDLTKSearchConstants.REFERENCES;
	}

	QuerySpecification createQuery(IModelElement element) throws ModelException {
		DLTKSearchScopeFactory factory = DLTKSearchScopeFactory.getInstance();
		boolean isInsideInterpreterEnvironment = factory.isInsideInterpreter(element);

		IDLTKSearchScope scope = factory.createWorkspaceScope(isInsideInterpreterEnvironment, getLanguageToolkit());
		String description = factory.getWorkspaceScopeDescription(isInsideInterpreterEnvironment);
		return new ElementQuerySpecification(element, getLimitTo(), scope, description);
	}

	public void run(IModelElement element) {
//		SearchUtil.warnIfBinaryConstant(element, getShell());
		super.run(element);
	}
}
