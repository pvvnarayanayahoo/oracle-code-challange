package com.test.coding.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.test.coding.project.model.Project;
import com.test.coding.project.service.ProjectUtilityService;

/** @author Narayana */
public class ProjectApp 
{
    /** @param args */
    public static void main( String[] args )
    {
    	ProjectApp app = new ProjectApp();
    	app.process();
    }
    
    
    /** process method to process all required operations */
    private void process() {
    	
    	/** read input for all project details as multiline string */
    	List<String> lines = readInput();
    	
    	/** convert string data into java object */
    	List<Project> projects = lines.stream().map(Project::toProjectObject).collect(Collectors.toList());
    	
    	final Map<String, Long> uniqCustomersPerContractId = ProjectUtilityService.getUniqueCustomersPerContractId(projects);
    	System.out.println("The number of unique customerId for each contractId (ContractId & No.Of customer ids)");
    	System.out.println("=============================================================================");
    	uniqCustomersPerContractId.entrySet().stream().forEach(System.out::println);
    	
    	System.out.println();
    	final Map<String, Long> uniqCustomersPerGeozone = ProjectUtilityService.getUniqueCustomersPerGeozone(projects);
    	System.out.println("The number of unique customerId for each geozone (Geozone & No.Of customer ids)");
    	System.out.println("=============================================================================");
    	uniqCustomersPerGeozone.entrySet().stream().forEach(System.out::println);
    	
    	System.out.println();
    	final Map<String, Double> averageBuildDurationPerGeozone = ProjectUtilityService.getAveragebuildDurationPerGeozone(projects);
    	System.out.println("The average buildduration for each geozone (Geozone & Average build duration in seconds)");
    	System.out.println("=============================================================================");
    	averageBuildDurationPerGeozone.entrySet().stream().forEach(System.out::println);
    	
    	System.out.println();
    	final Map<Object, List<String>> uniqCustomersListPerGeozone = ProjectUtilityService.getUniqueCustomersListPerGeozone(projects);
    	System.out.println("The list of unique customerId for each geozone (Geozone & List of customer ids)");
    	System.out.println("=============================================================================");
    	uniqCustomersListPerGeozone.entrySet().stream().forEach(System.out::println);
		
	}




	/**
     * @return {@link List} 
     * <br>this method is to read input for all project details as multiline string
     */
    private static List<String> readInput(){
    	System.out.println("Enter project details:\n");
    	final List<String> lines = new ArrayList<String>();
    	Scanner scanner = new Scanner(System.in);
    	 
        while (scanner.hasNextLine()) {
        	String line = scanner.nextLine();
        	if(line == null || line.isEmpty()) //if the line is empty
                break;  //exit the loop
            lines.add(line);
        }
 
        scanner.close();//close the scanner
        return lines;
    }
}
