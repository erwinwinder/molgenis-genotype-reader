package org.molgenis.genotype;

import java.io.File;

import org.molgenis.genotype.tabix.TabixIndexTest;

public class ResourceTest
{
	protected File getTestResourceFile(String name)
	{
		return new File(TabixIndexTest.class.getResource(name).getFile());
	}

	protected File getTestVcfGz()
	{
		return getTestResourceFile("/test.vcf.gz");
	}

	protected File getTestVcfGzTbi()
	{
		return getTestResourceFile("/test.vcf.gz.tbi");
	}

	protected File getTestMapGz()
	{
		return getTestResourceFile("/test.map.gz");
	}

	protected File getTestMapGzTbi()
	{
		return getTestResourceFile("/test.map.gz.tbi");
	}

	protected File getTestPed()
	{
		return getTestResourceFile("/test.ped");
	}
}
