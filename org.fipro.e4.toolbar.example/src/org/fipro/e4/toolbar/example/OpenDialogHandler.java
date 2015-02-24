 
package org.fipro.e4.toolbar.example;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;

public class OpenDialogHandler {
	@Execute
	public void execute() {
		MessageDialog.openInformation(null, "Information", "Show an information!");
	}
		
}