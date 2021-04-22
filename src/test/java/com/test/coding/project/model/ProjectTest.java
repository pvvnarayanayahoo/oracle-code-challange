package com.test.coding.project.model;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * this is a test class for project model class
 * @author Narayana
 */
public class ProjectTest {
	
	private static Project project = null;

	/**
	 * initializing project object with dummy data for test cases
	 */
	@Before
	public void init() {
		project = new Project("2343225","2345","us_east","RedTeam","ProjectApple","3445s");
	}
	
	/**
	 * this method will test build duration value which was initialized with all constructor
	 * @throws Exception
	 */
	@Test
	public void testProjectAllArgs() throws Exception {
		assertEquals(3445, project.getBuildDuration());
	}

	/**
	 * setting build duration value as string and verifying that the returned value should be integer value
	 * @throws Exception
	 */
	@Test
	public void testSetBuildDurationString() throws Exception {
		project.setBuildDuration("1234s");
		assertEquals(1234, project.getBuildDuration());
	}

	/**
	 * this test case is to verify the string data is converted to Project object 
	 * @throws Exception
	 */
	@Test
	public void testToProjectObject() throws Exception {
		String projectData = "2343225,2345,us_east,RedTeam,ProjectApple,3445s";
		Project p = Project.toProjectObject(projectData);
		assertEquals("2343225",p.getCustomerId());
		
	}

}
