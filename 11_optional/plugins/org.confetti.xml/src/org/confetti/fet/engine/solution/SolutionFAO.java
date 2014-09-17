package org.confetti.fet.engine.solution;

import java.net.URL;
import java.text.MessageFormat;

import org.confetti.xml.FAOException;
import org.confetti.xml.GenericFAO;

/**
 * @author Gabor Bubla
 */
public class SolutionFAO extends GenericFAO<SolutionXML> {

	/** The classes bound by this FAO. */
	private static final Class<?>[] CLASSES = new Class[] {
		SolutionXML.class, 
		};

	@Override
	protected Class<?>[] getBoundedClasses() {
		return CLASSES;
	}

	@Override
	protected URL getSchemaURL() {
		return SolutionFAO.class.getResource("solution.xsd"); //$NON-NLS-1$
	}
	
	/**
	 * Writes the XML schema into the specified file.
	 *
	 * @param args the first argument has to be the file name.
	 */
	public static void main(final String[] args) {
		if (0 == args.length) {
			System.err.println("Usage: SolutionFAO <fileName>"); //$NON-NLS-1$
			return;
		}
		try {
			new SolutionFAO().saveSchema(args[0]);
			System.out.println(MessageFormat.format("The schema was saved to: {0}", args[0])); //$NON-NLS-1$
		} catch (FAOException e) {
			e.printStackTrace();
		}
	}
}
