package org.molgenis.genotype.variant;

import java.util.List;

import org.molgenis.genotype.Alleles;

public class SwappingSampleVariantsProvider implements SampleVariantsProvider
{
	private SampleVariantsProvider sampleVariantsProvider;

	public SwappingSampleVariantsProvider(SampleVariantsProvider sampleVariantsProvider)
	{
		this.sampleVariantsProvider = sampleVariantsProvider;
	}

	@Override
	public List<Alleles> getSampleVariants(GeneticVariantOld variant)
	{
		List<Alleles> alleles = sampleVariantsProvider.getSampleVariants(variant);
		for (int i = 0; i < alleles.size(); i++)
		{
			alleles.set(i, alleles.get(i).getComplement());
		}

		return alleles;
	}

}
