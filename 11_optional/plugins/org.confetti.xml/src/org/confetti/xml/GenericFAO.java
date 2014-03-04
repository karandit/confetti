package org.confetti.xml;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

/**
 * Generic file access object to export/import objects in XML format via JAXB.
 *
 * @param <T> the type of handled object. The XAO exports/imports the instances of this type.
 */
public abstract class GenericFAO<T> {

	//--------------------------------- abstract methods ---------------------------------------------------------------
	/**
	 * Returns the bounded classes handled by this FAO.
	 *
	 * @return the bounded classes.
	 */
	protected abstract Class< ? >[] getBoundedClasses();
	/**
	 * Returns the URL to the XML schema.
	 *
	 * @return the URL to the XML schema.
	 */
//	protected abstract URL getSchemaURL();

	//--------------------------------- export methods -----------------------------------------------------------------
	/**
	 * Exports the given <code>item</code> into the given output stream.
	 *
	 * @param item the object to export.
	 * @param outputStream the target output stream.
	 * @throws FAOException if any error occurs while exporting.
	 */
	public void exportTo(final T item, final OutputStream outputStream) throws FAOException  {
		try {
			final JAXBContext context = getContext();
			final Marshaller m = context.createMarshaller();
//			m.setSchema(getSchema());
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(item, outputStream);
		} catch (final JAXBException //| SAXException  
				e) {
			throw new FAOException(e);
		}
	}

	/**
	 * Exports the given <code>item</code> into the given <code>file</code>.
	 *
	 * @param item the object to export.
	 * @param file the target file.
	 * @throws FAOException if any error occurs while exporting.
	 */
	public void exportTo(final T item, final File file) throws FAOException {
		try (FileOutputStream fos = new FileOutputStream(file)) {
			exportTo(item, fos);
		} catch (final IOException e) {
			throw new FAOException(e);
		}
	}

	/**
	 * Exports the given <code>item</code> into the given file.
	 *
	 * @param item the object to export.
	 * @param fileName the name of target file .
	 * @throws FAOException if any error occurs while exporting.
	 */
	public void exportTo(final T item, final String fileName) throws FAOException {
		exportTo(item, new File(fileName));
	}

	//--------------------------------- import methods -----------------------------------------------------------------
	/**
	 * Imports an object from the given input stream.
	 *
	 * @param inputStream the input stream.
	 * @return the imported object.
	 * @throws FAOException if any error occurs while importing.
	 */
	public T importFrom(final InputStream inputStream) throws FAOException  {
		try {
			final JAXBContext context = getContext();
			final Unmarshaller um = context.createUnmarshaller();
			//ValidationEventCollector vec = new ValidationEventCollector();
			//um.setEventHandler(vec);
			//um.setSchema(getSchema());
			@SuppressWarnings("unchecked")
			T unmarshal = (T) um.unmarshal(inputStream);
			//ValidationEvent[] events = vec.getEvents();
			return unmarshal;
		} catch (final JAXBException 
				//| SAXException 
				e
				) {
			throw new FAOException(e);
		}
	}

	/**
	 * Imports an object from the given <code>file</code>.
	 *
	 * @param file the source of import.
	 * @return the imported object.
	 * @throws FAOException if any error occurs while importing.
	 */
	public T importFrom(final File file) throws FAOException  {
		try (FileInputStream fis = new FileInputStream(file)) {
			return importFrom(fis);
		} catch (final IOException e) {
			throw new FAOException(e);
		}
	}

	/**
	 * Imports an object from the given file.
	 *
	 * @param fileName the name of file to import.
	 * @return the imported object.
	 * @throws FAOException if any error occurs while importing.
	 */
	public T importFrom(final String fileName) throws FAOException  {
		return importFrom(new File(fileName));
	}

	//--------------------------------- utility methods ----------------------------------------------------------------
//	/**
//	 * Instantiates a new schema.
//	 *
//	 * @return the newly created schema
//	 * @throws SAXException if any exception occurred during the instantiation
//	 */
//	protected Schema getSchema() throws SAXException {
//		final SchemaFactory sf = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
//		return sf.newSchema(getSchemaURL());
//	}

	/**
	 * Saves the schema to to given file.
	 *
	 * @param fileName the path of the file.
	 * @throws FAOException if any exception occurred during the save
	 */
	protected void saveSchema(final String fileName) throws FAOException {
		try {
			getContext().generateSchema(new SchemaOutputResolver() {
				@Override
				public Result createOutput(final String namespaceUri, final String suggestedFileName) {

					StreamResult r = new StreamResult(new File(fileName));
					r.setSystemId(fileName);
					return r;
				}
			});
		} catch (IOException | JAXBException  e) {
			throw new FAOException(e);
		}
	}

	/**
	 * Instantiates a new {@link JAXBContext}.
	 *
	 * @return a new JAXB context.
	 * @throws JAXBException if any error occurs while instantiating the JAXB context.
	 * @see JAXBContext#newInstance(Class...)
	 */
	protected JAXBContext getContext() throws JAXBException {
		return JAXBContext.newInstance(getBoundedClasses());
	}
}
