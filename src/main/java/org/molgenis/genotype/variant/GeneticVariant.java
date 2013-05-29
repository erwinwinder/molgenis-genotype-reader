package org.molgenis.genotype.variant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.molgenis.genotype.Alleles;
import org.molgenis.genotype.util.Ld;
import org.molgenis.genotype.util.LdCalculatorException;
import org.molgenis.genotype.variant.id.GeneticVariantId;

public interface GeneticVariant
{

	/**
	 * Get the primary variant ID
	 * 
	 * @return String
	 */
	public String getPrimaryVariantId();

	/**
	 * Gets all the other id's (names) besides the primaryVariantId by which
	 * this variant is known.
	 * 
	 * @return List of String
	 */
	public List<String> getAlternativeVariantIds();

	/**
	 * Get all IDs for this variant
	 * 
	 * @return List of String
	 */
	public List<String> getAllIds();

	/**
	 * Get the variant ID object for this variant
	 * 
	 * @return
	 */
	public GeneticVariantId getVariantId();

	/**
	 * Gets the starting position on the sequence
	 * 
	 * @return int
	 */
	public int getStartPos();

	/**
	 * Get the Sequence this variant is located on
	 * 
	 * @return the Sequence
	 */
	public String getSequenceName();

	/**
	 * Get all possible alleles (including the reference) The first value is the
	 * reference value if it is set
	 * 
	 * @return
	 */
	public ArrayList<String> getVariantAlleles();

	/**
	 * Get all possible SNP alleles (including the reference) The first value is
	 * the reference value if it is set
	 * 
	 * @return
	 * @throws NotASnpException
	 *             if not a SNP
	 */
	public char[] getVariantSnpAlleles() throws NotASnpException;

	/**
	 * Get the total allele count
	 * 
	 * @return
	 */
	public int getAlleleCount();

	/**
	 * Gets the reference allele
	 * 
	 * @return String
	 */
	public String getRefAllele();

	/**
	 * get the SNP ref allele
	 * 
	 * @return
	 * @throws NotASnpException
	 *             if not a SNP
	 */
	public char getSnpRefAllele() throws NotASnpException;

	/**
	 * Returns list sample variants. The list of variants can contain null !!!!
	 * if unknown
	 */
	public List<Alleles> getSampleVariants();

	/**
	 * Get the annotations for this variant. The key is the annotationId, the
	 * value is of the type defined by the Annotation (see
	 * GenotypeData.getVariantAnnotations()
	 * 
	 * @return
	 */
	public Map<String, ?> getAnnotationValues();

	/**
	 * Get the frequency of the minor allele
	 * 
	 * @return the minor allele frequency
	 */
	public float getMinorAlleleFrequency();

	/**
	 * Get the minor allele
	 * 
	 * @return the minor allele
	 */
	public String getMinorAllele();

	/**
	 * Is this variant a SNP
	 * 
	 * @return true if SNP
	 */
	public boolean isSnp();

	/**
	 * Is this variant an AT or GC SNP.
	 * 
	 * @return true if SNP and AT or GC
	 */
	public boolean isAtOrGcSnp();

	/**
	 * Calculate LD to an other variant
	 * 
	 * @param other
	 * @return
	 * @throws LdCalculatorException
	 */
	public Ld calculateLd(GeneticVariantOld other) throws LdCalculatorException;

	/**
	 * Test if biallelic
	 * 
	 * @return
	 */
	public boolean isBiallelic();

	/**
	 * Get the dosage values. -1 for unknown
	 * 
	 * @return dosage values
	 */
	public float[] getSampleDosages();

	/**
	 * Get the sample variant provider used by this variant
	 * 
	 * @return
	 */
	public SampleVariantsProvider getSampleVariantsProvider();

}
