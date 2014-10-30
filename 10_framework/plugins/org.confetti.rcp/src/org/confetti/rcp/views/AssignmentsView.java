package org.confetti.rcp.views;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.core.Subject;
import org.confetti.observable.ObservableListener;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableNoScrollModel;

public class AssignmentsView extends ViewPart {

	public final static String ID = "org.confetti.rcp.assignmentsView";
	
//	private ObservableListener<String> nameListener;
	
	@Override
	public void createPartControl(Composite parent) {
		SashForm form = new SashForm(parent, SWT.VERTICAL);
		form.setLayout(new FillLayout());
		TableViewer tableViewer = createAssigmentsList(form);
		KTable ktable = createTimeTable(form);
		assignListener(tableViewer, ktable);
	}

	private TableViewer createAssigmentsList(Composite parent) {
		Table table = new Table(parent, SWT.MULTI | SWT.FULL_SELECTION | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		table.setHeaderVisible(true);
		AbstractEntityTableView.createColumn(table, "#", 50);
		AbstractEntityTableView.createColumn(table, "Subject", 150);
		AbstractEntityTableView.createColumn(table, "Teacher", 150);
		AbstractEntityTableView.createColumn(table, "Student Group", 150);
		AbstractEntityTableView.createColumn(table, "Room", 150);
		
		TableViewer tableViewer = new TableViewer(table);
		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new AssignmentLabelProvider());
		
//		nameListener = new ObservableListener<String>() {
//			@Override
//			public void valueChanged(Object src, String oldValue, String newValue) {
//				tableViewer.refresh();
//			}
//		};
		
		//create context menu
		MenuManager menuManager = new MenuManager();
		Menu menu = menuManager.createContextMenu(tableViewer.getControl());
		tableViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuManager, tableViewer);
		getSite().setSelectionProvider(tableViewer);
		
		return tableViewer;
	}
	
	private KTable createTimeTable(Composite parent) {
		final KTable ktable = new KTable(parent, SWT.NONE);
		ktable.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		
		assignModel(ktable, null, null);
		
		ConfettiPlugin.getDefault().getDataProvider().attachListener(new ObservableListener<DataProvider>() {
			@Override
			public void valueChanged(Object src, DataProvider oldValue, DataProvider newValue) {
				if (newValue != null) {
					assignModel(ktable, newValue, null);
				}
			}
		});
		return ktable;
	}

	private void assignListener(final TableViewer tableViewer, final KTable ktable) {
		ISelectionService selectionService = this.getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				//do nothing when the selection comes from this view
			    if (AssignmentsView.ID.equals(part.getSite().getId())) {
				    return;
				}
			    
			    //empty the view when selection is empty
				if (selection.isEmpty()) {
				    tableViewer.setInput(null);
				    assignModel(ktable, ConfettiPlugin.getDefault().getDataProvider().getValue(), null);
                    return;
                }
				
				//if the selection is Entity show it's Assignments and SolutionSlot 
				IStructuredSelection strSel = (IStructuredSelection) selection;
				Object first = strSel.getFirstElement();
				if (first instanceof Entity) {
					Entity source = (Entity) first;
					//TODO detach this listener somewhere? :/
//					source.getName().attachListener(nameListener);
					tableViewer.setInput(source.getAssignments().getList());
					if (source instanceof Subject) {
						assignModel(ktable, ConfettiPlugin.getDefault().getDataProvider().getValue(), null);
					} else {
						assignModel(ktable, ConfettiPlugin.getDefault().getDataProvider().getValue(), source);
					}
					return;
				}
			}
		});
	}

	private void assignModel(final KTable ktable, DataProvider dp, Entity ent) {
		final KTableNoScrollModel model;
		if (dp == null) {
			model = new TimeTableNotAvailableModel(ktable);
			ktable.setModel(model);
			model.initialize();
			return;
		}
		model = new TimeTableModel(ktable, dp, ent);
		ktable.setModel(model);
		model.initialize();
	}

	@Override
	public void setFocus() {
	}

	private static class AssignmentLabelProvider extends LabelProvider implements ITableLabelProvider {

		@Override public Image getColumnImage(Object element, int columnIndex) { return null; }

		@Override
		public String getColumnText(Object element, int columnIndex) {
			Assignment assignment = (Assignment) element;
			switch (columnIndex) {
				case 0: return "1/4";
				case 1:	return getName(assignment.getSubject());
				case 2:	return toStr(assignment.getTeachers().getList());
				case 3:	return toStr(assignment.getStudentGroups().getList());
				default : return getName(assignment.getRoom());
			}
		}

		private static String getName(Entity ent) { return ent == null ? null : ent.getName().getValue(); }

		private static <T extends Entity> String toStr(Iterable<T> items) {
			StringBuilder sb = new StringBuilder();
			boolean first = true;
			for (T t : items) {
				if (first) {
					first = false;
				} else {
					sb.append(", ");
				}
				sb.append(getName(t));
			}
			return sb.toString();
		}
	}
	
}
