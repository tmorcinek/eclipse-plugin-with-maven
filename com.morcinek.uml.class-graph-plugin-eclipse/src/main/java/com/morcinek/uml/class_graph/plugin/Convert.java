package com.morcinek.uml.class_graph.plugin;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

public class Convert extends AbstractHandler {

	public Object execute(ExecutionEvent arg0) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(arg0);
		try {
			Object firstElement = selection.getFirstElement();
			IProjectNature resource = (IProjectNature) firstElement;
			IPath path = resource.getProject().getLocation();

			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(arg0);
			MessageDialog.openInformation(window.getShell(), "Selected Project", path.toOSString());
		} catch (Exception e) {
			e.printStackTrace();
			showErrorMessage(e);
		}
		return null;
	}

	private void showErrorMessage(Exception e) {
		showErrorMessage(e.getMessage());
	}

	private void showErrorMessage(String message) {
		MessageDialog.openError(new Shell(), "Eclipse Maven Plugin", message);
	}

}
