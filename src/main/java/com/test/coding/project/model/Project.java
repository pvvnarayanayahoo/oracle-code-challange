package com.test.coding.project.model;

/**
 * @author Narayana
 *<br> {@link Project} class
 */
public class Project {
	
	private String customerId;
	private String contractId;
	private String geozone;
	private String teamCode;
	private String projectCode;
	private int buildDuration;
	
	public Project() {
	}
	
	/**
	 * @param customerId
	 * @param contractId
	 * @param geozone
	 * @param teamCode
	 * @param projectCode
	 * @param buildDuration
	 * <br>this all args constructor will assign values to instance variables
	 * and convert build duration from string to int value
	 */
	public Project(String customerId,String contractId,String geozone,String teamCode, String projectCode, String buildDuration) {
		
		this.customerId = customerId;
		this.contractId = contractId;
		this.geozone = geozone;
		this.teamCode = teamCode;
		this.projectCode = projectCode;
		this.buildDuration = convertBuildDurationToInt(buildDuration);
	}

	/**
	 * @param buildDuration
	 * @return int
	 * <br>it converts a string value value into an integer
	 */
	private int convertBuildDurationToInt(String buildDuration) {
		int _buildDuration = Integer.parseInt(buildDuration.toLowerCase().replaceAll("s", ""));
		return _buildDuration;
	}
	
	
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getGeozone() {
		return geozone;
	}

	public void setGeozone(String geozone) {
		this.geozone = geozone;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public int getBuildDuration() {
		return buildDuration;
	}

	public void setBuildDuration(int buildDuration) {
		this.buildDuration = buildDuration;
	}

	public void setBuildDuration(String buildDuration) {
		this.buildDuration = convertBuildDurationToInt(buildDuration);
	}

	@Override
	public String toString() {
		return "Project [customerId=" + customerId + ", contractId=" + contractId + ", geozone=" + geozone
				+ ", teamCode=" + teamCode + ", projectCode=" + projectCode + ", buildDuration=" + buildDuration + "]";
	}
	
	/**
	 * @param unFormedData
	 * @return {@link Project}
	 * <br>This method is to convert unFormedData into a Java object called Project class
	 */
	public static Project toProjectObject(String unFormedData) {
		Project project = null;
		String[] data = unFormedData.split(",");
		
		if(data.length<6) // accepts only if all 6 fields data available otherwise setting empty object
			project = new Project();
		else
			project = new Project(data[0],data[1],data[2],data[3],data[4],data[5]);
		return project;
	}
	
}
