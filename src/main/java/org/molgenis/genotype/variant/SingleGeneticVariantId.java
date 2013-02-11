package org.molgenis.genotype.variant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SingleGeneticVariantId extends GeneticVariantId {

	private final String variantId;

	public SingleGeneticVariantId(String variantId) {
		super();
		this.variantId = variantId;
	}

	@Override
	public String getPrimairyId() {
		return variantId;
	}

	@Override
	public List<String> getVariantIds() {
		ArrayList<String> variantIds = new ArrayList<String>(1);
		return Collections.unmodifiableList(variantIds);
	}

	@Override
	public String getConcatenatedId() {
		return variantId;
	}

	@Override
	public String getConcatenatedId(String separator) {
		return variantId;
	}

	@Override
	public boolean isIdInVariantIds(String queryId) {
		return variantId.equals(queryId);
	}

	@Override
	public boolean onlyPrimairyId() {
		return true;
	}
	
	@Override
	public Iterator<String> iterator() {
		return getVariantIds().iterator();
	}

	@Override
	public List<String> getAlternativeIds() {
		return Collections.emptyList();
	}

}
