/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 
 *******************************************************************************/
package org.eclipse.dltk.mod.ui;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.CompositeImageDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;

public class ScriptElementImageDescriptor extends CompositeImageDescriptor
{
	/** Flag to render the abstract adornment. */
	public final static int ABSTRACT= 		0x001;
	
	/** Flag to render the final adornment. */
	public final static int FINAL=			0x002;
	
	/** Flag to render the static adornment. */
	public final static int STATIC=			0x008;
	
	/** Flag to render the 'constructor' adornment. */
	public final static int CONSTRUCTOR= 	0x200;
	
	/** Flag to render the warning adornment. */
	public final static int WARNING=			0x020;
	
	/** Flag to render the error adornment. */
	public final static int ERROR=			0x040;	
	
	private Point fSize;
	private int fFlags;
	
	ImageDescriptor fBaseImage;

	public ScriptElementImageDescriptor( ImageDescriptor baseImageDescriptor, int flags, Point size ) {
		fBaseImage= baseImageDescriptor;
		Assert.isNotNull(fBaseImage);
		fFlags= flags;
		Assert.isTrue(fFlags >= 0);
		fSize= size;
		Assert.isNotNull(fSize);
	}

	/**
	 * Sets the size of the image created by calling <code>createImage()</code>.
	 * 
	 * @param size the size of the image returned from calling <code>createImage()</code>
	 * @see ImageDescriptor#createImage()
	 */
	public void setImageSize(Point size) {
		Assert.isNotNull(size);
		Assert.isTrue(size.x >= 0 && size.y >= 0);
		fSize= size;
	}
	
	/**
	 * Returns the size of the image created by calling <code>createImage()</code>.
	 * 
	 * @return the size of the image created by calling <code>createImage()</code>
	 * @see ImageDescriptor#createImage()
	 */
	public Point getImageSize() {
		return new Point(fSize.x, fSize.y);
	}
	
	/* (non-Javadoc)
	 * Method declared in CompositeImageDescriptor
	 */
	protected Point getSize() {
		return fSize;
	}
	
	/* (non-Javadoc)
	 * Method declared on Object.
	 */
	public boolean equals(Object object) {		
		if (object == null || !ScriptElementImageDescriptor.class.equals(object.getClass())) {			
			return false;
		}
			
		ScriptElementImageDescriptor other= (ScriptElementImageDescriptor)object;
		
		if( this.fBaseImage == null ) {
			return false;
		}
		return (other.fFlags == this.fFlags) && (fBaseImage.equals(other.fBaseImage) == fSize.equals(other.fSize));
	}
	
	/* (non-Javadoc)
	 * Method declared on Object.
	 */
	public int hashCode() {
		if( this.fBaseImage != null ) {
			return fBaseImage.hashCode() | fSize.hashCode() + this.fFlags;
		}
		return fSize.hashCode();
	}

	private ImageData getImageData( ImageDescriptor descriptor ) {

		if( this.fBaseImage != null ) {
			ImageData data = descriptor.getImageData( ); // see bug 51965: getImageData can return null
			if( data == null ) {
				data = DEFAULT_IMAGE_DATA;
				System.err.println( "Image data not available: " + descriptor.toString( ) ); //$NON-NLS-1$
				// DLTKUIPlugin.logErrorMessage("Image data not available: " + descriptor.toString()); //$NON-NLS-1$
			}
			return data;
		}
		else {			
			System.err.println( "Image data not available: " + descriptor.toString( ) ); //$NON-NLS-1$
			return DEFAULT_IMAGE_DATA;
		}
	}

	
	protected void drawCompositeImage( int width, int height ) {
		ImageData bg= getImageData(fBaseImage);
		
		if( bg != null ) {
			drawImage(bg, 0, 0);
		}
		drawTopRight();
		drawBottomLeft();
	}
	
	private void drawBottomLeft() {
		Point size= getSize();
		int x= 0;
		if ((fFlags & ERROR) != 0) {
			ImageData data= getImageData(DLTKPluginImages.DESC_OVR_ERROR);
			drawImage(data, x, size.y - data.height);
			x += data.width;
		}
		if ((fFlags & WARNING) != 0) {
			ImageData data= getImageData(DLTKPluginImages.DESC_OVR_WARNING);
			drawImage(data, x, size.y - data.height);
			x+= data.width;
		}

	}		
	
	private void drawTopRight() {
		Point pos = new Point(getSize().x, 0);
		if ((fFlags & ABSTRACT) != 0) {
			addTopRightImage(DLTKPluginImages.DESC_OVR_ABSTRACT, pos);
		}
		if ((fFlags & CONSTRUCTOR) != 0) {
			addTopRightImage(DLTKPluginImages.DESC_OVR_CONSTRUCTOR, pos);
		}
		if ((fFlags & FINAL) != 0) {
			addTopRightImage(DLTKPluginImages.DESC_OVR_FINAL, pos);
		}
		if ((fFlags & STATIC) != 0) {
			addTopRightImage(DLTKPluginImages.DESC_OVR_STATIC, pos);
		}

	}

	private void addTopRightImage(ImageDescriptor desc, Point pos) {
		ImageData data = getImageData(desc);
		int x = pos.x - data.width;
		if (x >= 0) {
			drawImage(data, x, pos.y);
			pos.x = x;
		}
	}
}
