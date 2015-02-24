package org.fipro.e4.toolbar.example;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;

public class ToggleMenuItem {

	public static final String KEY = "debugEnabled"; //$NON-NLS-1$

	@Inject
	IEventBroker eventBroker;
	
	@Execute
	public void execute(IEclipseContext context) {
		Boolean visible = (Boolean) context.get(KEY);
		context.modify(KEY, !visible);
		
		eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
				UIEvents.ALL_ELEMENT_ID);
	}
}
