package org.fipro.e4.toolbar.example;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;

public class LifecycleHook {

	@ProcessAdditions
	public void processAdditions(IEclipseContext context) {
		context.set(ToggleMenuItem.KEY, Boolean.FALSE);
		context.declareModifiable(ToggleMenuItem.KEY);
	}
}
