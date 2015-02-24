 
package org.fipro.e4.toolbar.example;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class SimplePart {
	
	public static final String KEY = "debugEnabled"; //$NON-NLS-1$
	
	@Inject
	IEventBroker eventBroker;
	
	Button debugOn;
	Button debugOff;
	
	@PostConstruct
	public void postConstruct(Composite parent, final IEclipseContext context) {
		parent.setLayout(new RowLayout());
		
		debugOn = new Button(parent, SWT.PUSH);
		debugOn.setText("Debug On");
		
		debugOff = new Button(parent, SWT.PUSH);
		debugOff.setText("Debug Off");

		boolean debugEnabled = (Boolean) context.get(KEY);
		if (debugEnabled) {
			debugOn.setEnabled(false);
			debugOff.setEnabled(true);
		}
		else {
			debugOn.setEnabled(true);
			debugOff.setEnabled(false);
		}
		
		debugOn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				context.modify(KEY, true);
				
				eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
						UIEvents.ALL_ELEMENT_ID);
				
				debugOn.setEnabled(false);
				debugOff.setEnabled(true);
			}
		});
		
		
		debugOff.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				context.modify(KEY, false);
				
				eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
						UIEvents.ALL_ELEMENT_ID);
				
				debugOn.setEnabled(true);
				debugOff.setEnabled(false);
			}
		});
	}
	
	@Inject
	@Optional
	private void subscribeToEnablement(
			@UIEventTopic(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC) String test, 
			@Named(KEY) Boolean debugEnabled) {
		
		debugOn.setEnabled(!debugEnabled);
		debugOff.setEnabled(debugEnabled);
	}
}