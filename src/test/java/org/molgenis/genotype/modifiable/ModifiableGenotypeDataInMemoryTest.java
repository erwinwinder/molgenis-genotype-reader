package org.molgenis.genotype.modifiable;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.molgenis.genotype.Allele;
import org.molgenis.genotype.GenotypeDataException;
import org.molgenis.genotype.RandomAccessGenotypeData;
import org.molgenis.genotype.ResourceTest;
import org.molgenis.genotype.plink.PedMapGenotypeData;
import org.molgenis.genotype.variant.GeneticVariant;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ModifiableGenotypeDataInMemoryTest extends ResourceTest
{

	private RandomAccessGenotypeData originalGenotypeData;
	private ModifiableGenotypeDataInMemory modifiableGenotypeData;

	@BeforeMethod
	public void setup() throws FileNotFoundException, IOException, URISyntaxException
	{
		originalGenotypeData = new PedMapGenotypeData(getTestPed(), getTestMap());
		modifiableGenotypeData = new ModifiableGenotypeDataInMemory(originalGenotypeData);
	}

	@Test
	public void getModifiableGeneticVariants()
	{
		Iterator<GeneticVariant> originalGeneticVariants = originalGenotypeData.iterator();
		Iterator<ModifiableGeneticVariant> modifiableGeneticVariants = modifiableGenotypeData
				.getModifiableGeneticVariants().iterator();

		assertEqualsVariantIterators(originalGeneticVariants, modifiableGeneticVariants);

	}

	@Test
	public void getModifiableSequenceGeneticVariants()
	{
		Iterator<GeneticVariant> originalGeneticVariants = originalGenotypeData.getSequenceGeneticVariants("22")
				.iterator();
		Iterator<ModifiableGeneticVariant> modifiableGeneticVariants = modifiableGenotypeData
				.getModifiableSequenceGeneticVariants("22").iterator();

		assertEqualsVariantIterators(originalGeneticVariants, modifiableGeneticVariants);
	}

	@Test
	public void getModifiableSnpVariantByPos()
	{
		GeneticVariant originalVariant = originalGenotypeData.getSnpVariantByPos("22", 14433624);
		ModifiableGeneticVariant modifiableGeneticVariant = modifiableGenotypeData.getModifiableSnpVariantByPos("22",
				14433624);

		assertEquals(modifiableGeneticVariant.getSequenceName(), originalVariant.getSequenceName());
		assertEquals(modifiableGeneticVariant.getStartPos(), originalVariant.getStartPos());

	}

	@Test
	public void getModifiableVariantsByPos()
	{
		Iterator<GeneticVariant> originalGeneticVariants = originalGenotypeData.getVariantsByPos("22", 14433624)
				.iterator();
		Iterator<ModifiableGeneticVariant> modifiableGeneticVariants = modifiableGenotypeData
				.getModifiableVariantsByPos("22", 14433624).iterator();

		assertEqualsVariantIterators(originalGeneticVariants, modifiableGeneticVariants);
	}

	@Test
	public void getSamples()
	{
		assertEquals(modifiableGenotypeData.getSamples(), originalGenotypeData.getSamples());
	}

	@Test
	public void getSeqNames()
	{
		assertEquals(modifiableGenotypeData.getSeqNames(), originalGenotypeData.getSeqNames());
	}

	@Test
	public void getSequenceByName()
	{
		assertEquals(modifiableGenotypeData.getSequenceByName("22"), originalGenotypeData.getSequenceByName("22"));
	}

	@Test
	public void getSequenceGeneticVariants()
	{
		assertEquals(modifiableGenotypeData.getSequenceGeneticVariants("22"),
				originalGenotypeData.getSequenceGeneticVariants("22"));
	}

	@Test
	public void getSequences()
	{
		assertEquals(modifiableGenotypeData.getSequences(), originalGenotypeData.getSequences());
	}

	@Test
	public void getSnpVariantByPos()
	{
		// Make sure not to use this variant to test updating otherwise this
		// might fail depending on the run order of the tests
		assertEquals(modifiableGenotypeData.getSnpVariantByPos("22", 14433624),
				originalGenotypeData.getSnpVariantByPos("22", 14433624));
	}

	@Test
	public void getVariantsByPos()
	{
		assertEquals(modifiableGenotypeData.getVariantsByPos("22", 14433624),
				originalGenotypeData.getVariantsByPos("22", 14433624));
	}

	@Test(expectedExceptions = GenotypeDataException.class)
	public void testIllegalRefAllele()
	{

		ModifiableGeneticVariant modifiableGeneticVariant = modifiableGenotypeData.getModifiableSnpVariantByPos("22",
				14431347);

		modifiableGeneticVariant.updateRefAllele(Allele.T_ALLELE);

	}

	@Test
	public void updateRefAllele()
	{

		ModifiableGeneticVariant modifiableGeneticVariant = modifiableGenotypeData.getModifiableSnpVariantByPos("22",
				14431347);

		modifiableGeneticVariant.updateRefAllele(Allele.C_ALLELE);

		assertEquals(modifiableGeneticVariant.getRefAllele(), Allele.C_ALLELE);

		assertEquals(modifiableGenotypeData.getSnpVariantByPos("22", 14431347).getRefAllele(), Allele.C_ALLELE);

		// TODO fix test
		// for (GeneticVariant variant : modifiableGenotypeData)
		// {
		// if (variant.getStartPos() == 14431347)
		// {
		// assertEquals(variant.getRefAllele(), Allele.C_ALLELE);
		// }
		// }
		//
		// for (GeneticVariant variant :
		// modifiableGenotypeData.getSequenceGeneticVariants("22"))
		// {
		// if (variant.getStartPos() == 14431347)
		// {
		// assertEquals(variant.getRefAllele(), Allele.C_ALLELE);
		// }
		// }
		//
		// for (GeneticVariant variant :
		// modifiableGenotypeData.getVariantsByPos("22", 14431347))
		// {
		// if (variant.getStartPos() == 14431347)
		// {
		// assertEquals(variant.getRefAllele(), Allele.C_ALLELE);
		// }
		// }

	}

	public static void assertEqualsVariantIterators(Iterator<GeneticVariant> originalGeneticVariants,
			Iterator<ModifiableGeneticVariant> modifiableGeneticVariants)
	{

		while (originalGeneticVariants.hasNext() && modifiableGeneticVariants.hasNext())
		{
			GeneticVariant originalVariant = originalGeneticVariants.next();
			GeneticVariant modifiableVariant = modifiableGeneticVariants.next();

			assertEquals(modifiableVariant.getSequenceName(), originalVariant.getSequenceName());
			assertEquals(modifiableVariant.getStartPos(), originalVariant.getStartPos());

		}

		if (originalGeneticVariants.hasNext() || modifiableGeneticVariants.hasNext())
		{
			throw new AssertionError("Number of original genetic variatiants is not identical to modifiable variants");
		}

	}
}
