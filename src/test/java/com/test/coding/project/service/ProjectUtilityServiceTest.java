package com.test.coding.project.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.test.coding.project.model.Project;

/**
 * this test class is for testing cases of project utility class
 * @author Narayana
 */
public class ProjectUtilityServiceTest {
	
	private final static List<Project> projects = new ArrayList<>();
	ProjectUtilityService utilityService = null;
	
	/**
	 * create and initialize required project objects and add them to list of projects object
	 */
	@Before
	public void init() {
		Project p1 = new Project("2343225","2345","us_east","RedTeam","ProjectApple","3445s");
		Project p2 = new Project("1223456","2345","us_west","BlueTeam","ProjectBanana","2211s");
		Project p3 = new Project("3244332","2346","eu_west","YellowTeam3","ProjectCarrot","4322s");
		Project p4 = new Project("1233456","2345","us_west","BlueTeam","ProjectDate","2221s");
		Project p5 = new Project("3244132","2346","eu_west","YellowTeam3","ProjectEgg","4122s");
		
		Collections.addAll(projects, p1,p2,p3,p4,p5);
		
		utilityService = ProjectUtilityService.getInstance();
		
	}

	/**
	 * this test cases is to getUniqueCustomersPerContractId 
	 * and asserting the results with expected data
	 * @throws Exception
	 */
	@Test
	public void testGetUniqueCustomersPerContractId() throws Exception {
		Map<String, Long> result = utilityService.getUniqueCustomersPerContractId(projects);
		Set<String> keys = result.keySet();
		assertTrue(keys.contains("2345"));
		assertTrue(keys.contains("2346"));
		assertEquals(new Long(2), result.get("2346"));
		assertEquals(new Long(3), result.get("2345"));
	}

	/**
	 * this test cases is to getUniqueCustomersPerGeozone 
	 * and asserting the results with expected data
	 * @throws Exception
	 */
	@Test
	public void testGetUniqueCustomersPerGeozone() throws Exception {
		Map<String, Long> result = utilityService.getUniqueCustomersPerGeozone(projects);
		Set<String> keys = result.keySet();
		assertTrue(keys.contains("eu_west"));
		assertTrue(keys.contains("us_west"));
		assertTrue(keys.contains("us_east"));
		assertEquals(new Long(2), result.get("eu_west"));
		assertEquals(new Long(2), result.get("us_west"));
		assertEquals(new Long(1), result.get("us_east"));
	}

	/**
	 * this test cases is to getUniqueCustomersListPerGeozone 
	 * and asserting the results with expected data
	 * @throws Exception
	 */
	@Test
	public void testGetUniqueCustomersListPerGeozone() throws Exception {
		Map<Object, List<String>> result = utilityService.getUniqueCustomersListPerGeozone(projects);
		Set<Object> keys = result.keySet();
		assertTrue(keys.contains("eu_west"));
		assertTrue(keys.contains("us_west"));
		assertTrue(keys.contains("us_east"));
		
		List<String> eu_westList = result.get("eu_west");
		List<String> us_westList = result.get("us_west");
		List<String> us_eastList = result.get("us_east");
		
		assertEquals(2, eu_westList.size());
		assertEquals(2, us_westList.size());
		assertEquals(1, us_eastList.size());
		
		assertTrue(eu_westList.contains("3244332"));
		assertTrue(eu_westList.contains("3244132"));
		assertTrue(us_westList.contains("1223456"));
		assertTrue(us_westList.contains("1233456"));
		assertTrue(us_eastList.contains("2343225"));
		
	}

	/**
	 * this test cases is to getAveragebuildDurationPerGeozone 
	 * and asserting the results with expected data
	 * @throws Exception
	 */
	@Test
	public void testGetAveragebuildDurationPerGeozone() throws Exception {
		Map<String, Double> result = utilityService.getAveragebuildDurationPerGeozone(projects);
		Set<String> keys = result.keySet();
		assertTrue(keys.contains("eu_west"));
		assertTrue(keys.contains("us_west"));
		assertTrue(keys.contains("us_east"));
		assertEquals(new Double(4222.0), result.get("eu_west"));
		assertEquals(new Double(2216.0), result.get("us_west"));
		assertEquals(new Double(3445.0), result.get("us_east"));
	}

}
