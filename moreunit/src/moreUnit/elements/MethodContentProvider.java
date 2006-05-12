package moreUnit.elements;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * This content provider is used to show a list dialog of corresponding
 * testmethods if a use renames a method.
 * 
 * @author vera
 * 29.03.2006 21:35:05
 */
public class MethodContentProvider implements IStructuredContentProvider {
	
	List methods;
	
	public MethodContentProvider(List methods) {
		this.methods = methods;
	}

	public Object[] getElements(Object inputElement) {
		return methods.toArray();
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}

// $Log: not supported by cvs2svn $
// Revision 1.1  2006/04/14 17:14:22  gianasista
// Refactoring Support with dialog
//