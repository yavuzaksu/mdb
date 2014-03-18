package com.mdb.issues.domain;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({  "codeCoverage","projectName",  "severity", "total" })
public class MdbMetric{
	@JsonProperty("severity")
	private String severity;	
	private Integer total;	
	private String projectName;
	private Double codeCoverage;
	
	public Double getCodeCoverage() {
		return codeCoverage;
	}
	public void setCodeCoverage(Double codeCoverage) {
		this.codeCoverage = codeCoverage;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@Override
	public String toString() {
		return "IssueMetric [severity=" + severity + ", total=" + total
				+ ", projectName=" + projectName + ", codeCoverage="
				+ codeCoverage + "]";
	}
	
	
}