package org.confetti.tablix.xml;

import java.net.URL;
import java.text.MessageFormat;

import org.confetti.xml.FAOException;
import org.confetti.xml.GenericFAO;

public class TablixFAO extends GenericFAO<TablixXml> {

	@Override protected Class<?>[] getBoundedClasses() 	{ return new Class[] { TablixXml.class }; }
	@Override protected URL getSchemaURL() { return TablixFAO.class.getResource("tablix.xsd"); } //$NON-NLS-1$ 
	
	/**
	 * Writes the XML schema into the specified file.
	 *
	 * @param args the first argument has to be the file name.
	 */
	public static void main(final String[] args) {
		if (0 == args.length) {
			System.err.println("Usage: TablixFAO <fileName>"); //$NON-NLS-1$
			return;
		}
		try {
			new TablixFAO().saveSchema(args[0]);
			System.out.println(MessageFormat.format("The schema was saved to: {0}", args[0])); //$NON-NLS-1$
		} catch (FAOException e) {
			e.printStackTrace();
		}
	}
	
}
