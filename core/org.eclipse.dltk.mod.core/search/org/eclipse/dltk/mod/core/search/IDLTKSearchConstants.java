/*******************************************************************************
 * Copyright (c) 2000, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.core.search;

import org.eclipse.dltk.mod.internal.core.search.processing.IJob;

/**
 * <p>
 * This interface defines the constants used by the search engine.
 * </p>
 * <p>
 * This interface declares constants only; it is not intended to be implemented.
 * </p>
 */
public interface IDLTKSearchConstants {

	/**
	 * The nature of searched element or the nature of match in unknown.
	 */
	int UNKNOWN = -1;

	/* Nature of searched element */

	/**
	 * The searched element is a type, which may include classes, interfaces,
	 * enums, and annotation types.
	 */
	int TYPE = 0;

	/**
	 * The searched element is a method.
	 */
	int METHOD = 1;

	/**
	 * The searched element is a package.
	 */
	// int PACKAGE= 2;
	/**
	 * The searched element is a constructor.
	 */
	// int CONSTRUCTOR= 3;
	/**
	 * The searched element is a field.
	 */
	int FIELD = 2;

	/**
	 * The searched element is an annotation type. More selective than using
	 * {@link #TYPE}.
	 * 
	 */
	int ANNOTATION_TYPE = 8;

	int SCRIPT_FOLDER = 5;

	/* Nature of match */

	/**
	 * The search result is a declaration. Can be used in conjunction with any
	 * of the nature of searched elements so as to better narrow down the
	 * search.
	 */
	int DECLARATIONS = 0;

	/**
	 * The search result is a type that implements an interface or extends a
	 * class. Used in conjunction with either TYPE or CLASS or INTERFACE, it
	 * will respectively search for any type implementing/extending a type, or
	 * rather exclusively search for classes implementing/extending the type, or
	 * interfaces extending the type.
	 */
	int SATISFIER = 8;

	/**
	 * The search result is a reference. Can be used in conjunction with any of
	 * the nature of searched elements so as to better narrow down the search.
	 * References can contain implementers since they are more generic kind of
	 * matches.
	 */
	int REFERENCES = 1;

	/**
	 * The search result is a declaration, a reference, or an implementer of an
	 * interface. Can be used in conjunction with any of the nature of searched
	 * elements so as to better narrow down the search.
	 */
	int ALL_OCCURRENCES = 2;

	// /**
	// * When searching for field matches, it will exclusively find read
	// accesses, as
	// * opposed to write accesses. Note that some expressions are considered
	// both
	// * as field read/write accesses: for example, x++; x+= 1;
	// *
	// */
	// int READ_ACCESSES = 4;
	//	
	// /**
	// * When searching for field matches, it will exclusively find write
	// accesses, as
	// * opposed to read accesses. Note that some expressions are considered
	// both
	// * as field read/write accesses: for example, x++; x+= 1;
	// *
	// */
	// int WRITE_ACCESSES = 5;

	/**
	 * Ignore declaring type while searching result. Can be used in conjunction
	 * with any of the nature of match.
	 * 
	 */
	int IGNORE_DECLARING_TYPE = 0x10;

	/**
	 * Ignore return type while searching result. Can be used in conjunction
	 * with any of the nature of match. Note that:
	 * <ul>
	 * <li>for fields search, pattern will ignore field type</li>
	 * <li>this flag will have no effect for types search</li>
	 * </ul>
	 * 
	 */
	int IGNORE_RETURN_TYPE = 0x20;

	/* Waiting policies */

	/**
	 * The search operation starts immediately, even if the underlying indexer
	 * has not finished indexing the workspace. Results will more likely not
	 * contain all the matches.
	 */
	int FORCE_IMMEDIATE_SEARCH = IJob.ForceImmediate;
	/**
	 * The search operation throws an
	 * <code>org.eclipse.core.runtime.OperationCanceledException</code> if the
	 * underlying indexer has not finished indexing the workspace.
	 */
	int CANCEL_IF_NOT_READY_TO_SEARCH = IJob.CancelIfNotReady;
	/**
	 * The search operation waits for the underlying indexer to finish indexing
	 * the workspace before starting the search.
	 */
	int WAIT_UNTIL_READY_TO_SEARCH = IJob.WaitUntilReady;

}
