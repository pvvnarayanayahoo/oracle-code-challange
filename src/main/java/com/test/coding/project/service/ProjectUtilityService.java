package com.test.coding.project.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.test.coding.project.model.Project;

/**
 * This class is for utility methods related to project objects
 * this has methods 
 * <br>getUniqueCustomersPerContractId
 * <br>getUniqueCustomersPerGeozone
 * <br>getUniqueCustomersListPerGeozone
 * <br>getAveragebuildDurationPerGeozone
 * <br>
 * @author Narayana
 * 
 */
public class ProjectUtilityService {

	/**
	 * @param projects
	 * @return {@link Map}
	 * <br>this method is to getUniqueCustomersPerContractId 
	 * it accepts list of projects object and do filter by combination of keys for uniqueness
	 * and groups the values by contract id as key and count of customers as values into a Map
	 */
	public static Map<String, Long> getUniqueCustomersPerContractId(List<Project> projects) {
		
		final Map<String, Long> result = projects.stream()
				.filter(distinctByKeys(Project::getContractId,Project::getCustomerId))
				.collect(Collectors.groupingBy(p -> p.getContractId(), Collectors.counting()));
		
		return result;
	}
	
	/**
	 * @param projects
	 * @return {@link Map}
	 * <br>this method is to getUniqueCustomersPerGeozone
	 * it accepts list of projects object and do filter by combination of keys for uniqueness
	 * and groups the values by geozone as key and count of customers as values into a Map
	 */
	public static Map<String, Long> getUniqueCustomersPerGeozone(List<Project> projects) {
		
		final Map<String, Long> result = projects.stream()
				.filter(distinctByKeys(Project::getGeozone,Project::getCustomerId))
				.collect(Collectors.groupingBy(p -> p.getGeozone(), Collectors.counting()));
		
		return result;
	}
	
	/**
	 * @param projects
	 * @return {@link Map}
	 * <br>this method is to getUniqueCustomersListPerGeozone
	 * it accepts list of projects object and do filter by combination of keys for uniqueness
	 * and groups the values by geozone as key and list of customers as value into a Map
	 */
	public static Map<Object, List<String>> getUniqueCustomersListPerGeozone(List<Project> projects) {
		
		final Map<Object, List<String>> result = projects.stream()
				.filter(distinctByKeys(Project::getGeozone,Project::getCustomerId))
				.collect(Collectors.groupingBy(p -> p.getGeozone(),
						Collectors.mapping(Project::getCustomerId, Collectors.toList())));
		
		return result;
	}
	
	/**
	 * @param projects
	 * @return {@link Map}
	 * <br>this method is to getAveragebuildDurationPerGeozone
	 * it accepts list of projects object and groups the values by geozone as key 
	 * and average build duration as value into a Map
	 */
	public static Map<String, Double> getAveragebuildDurationPerGeozone(List<Project> projects) {
		
		Map<String, Double> result = projects.stream()
				.collect(Collectors.groupingBy(Project::getGeozone,
						Collectors.averagingInt(Project::getBuildDuration)));
		
		return result;
	}
	
	/**
	 * @param <T>
	 * @param keyExtractors
	 * @return {@link Predicate}
	 * <br>this method accepts  varargs as key extractors  and multiple keys are accepted
	 * it creates a key list contains values to check distinct combinations
	 * the list keys are inserted into a {@link ConcurrentHashMap} which stores unique and distinct keys
	 */
	@SafeVarargs
	private static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) 
	{
	  final Map<List<?>, Boolean> uniqueMap = new ConcurrentHashMap<>();
	  
	  return t -> 
	  {
	    final List<?> keys = Arrays.stream(keyExtractors)
	                .map(ke -> ke.apply(t))
	                .collect(Collectors.toList());
	     
	    return uniqueMap.putIfAbsent(keys, Boolean.TRUE) == null;
	  };
	}
}
