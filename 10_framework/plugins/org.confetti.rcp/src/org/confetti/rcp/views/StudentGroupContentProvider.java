package org.confetti.rcp.views;

import static com.google.common.collect.Iterables.isEmpty;
import static com.google.common.collect.Iterables.toArray;

import java.util.List;

import org.confetti.core.StudentGroup;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public enum StudentGroupContentProvider implements ITreeContentProvider {
	INSTANCE;
	
	@Override public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
    @Override public void dispose() { }
    @Override public Object[] getElements(Object parent)    { return ((List<?>) parent).toArray(); }
    @Override public Object getParent(Object child)         { return ((StudentGroup) child).getParent(); }
    @Override public boolean hasChildren(Object parent)     { return !isEmpty(((StudentGroup) parent).getChildren().getList()); }
    @Override public Object[] getChildren(Object parent)    { return toArray(((StudentGroup) parent).getChildren().getList(), StudentGroup.class); }
}