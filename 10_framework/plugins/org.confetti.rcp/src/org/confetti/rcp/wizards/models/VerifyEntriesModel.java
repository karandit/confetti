package org.confetti.rcp.wizards.models;

import java.util.List;

import org.confetti.rcp.wizards.models.Problem;
import org.confetti.util.Tuple;

/**
 * @author Bubla Gabor
 */
public interface VerifyEntriesModel {

	String getVerifyEntriesPageDescription();
	String getVerifyEntriesPageTitle();
	String getPageImageKey();
	List<Tuple<String, Problem>> getResult();

}
