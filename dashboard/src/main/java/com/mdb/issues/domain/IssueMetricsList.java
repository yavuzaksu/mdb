package com.mdb.issues.domain;

import java.util.ArrayList;
import java.util.List;

public class IssueMetricsList {
	private List<IssueMetric> mdbProjects = new ArrayList<>();

	public List<IssueMetric> getMdbProjects() {
		return mdbProjects;
	}

	public void setMdbProjects(List<IssueMetric> mdbProjects) {
		this.mdbProjects = mdbProjects;
	}

}

