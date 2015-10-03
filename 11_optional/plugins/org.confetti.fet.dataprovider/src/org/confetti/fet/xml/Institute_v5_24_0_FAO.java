package org.confetti.fet.xml;

import java.net.URL;
import java.text.MessageFormat;

import org.confetti.fet.xml.core.Institute_v5_24_0_Xml;
import org.confetti.xml.FAOException;
import org.confetti.xml.GenericFAO;

public class Institute_v5_24_0_FAO extends GenericFAO<Institute_v5_24_0_Xml> {

	/** The classes bound by this FAO. */
	private static final Class<?>[] CLASSES = new Class[] {
		Institute_v5_24_0_Xml.class, 
	};

	@Override
	protected Class<?>[] getBoundedClasses() {
		return CLASSES;
	}

	@Override
	protected URL getSchemaURL() {
		return Institute_v5_24_0_FAO.class.getResource("institute_v5_24_0.xsd"); //$NON-NLS-1$
	}
	
	/**
	 * Writes the XML schema into the specified file.
	 *
	 * @param args the first argument has to be the file name.
	 */
	public static void main(final String[] args) {
		if (0 == args.length) {
			System.err.println("Usage: Institute_v5_24_0_FAO <fileName>"); //$NON-NLS-1$
			return;
		}
		try {
			new Institute_v5_24_0_FAO().saveSchema(args[0]);
			System.out.println(MessageFormat.format("The schema was saved to: {0}", args[0])); //$NON-NLS-1$
		} catch (FAOException e) {
			e.printStackTrace();
		}
	}
}
