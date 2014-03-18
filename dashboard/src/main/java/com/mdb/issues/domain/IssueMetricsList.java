package com.mdb.issues.domain;

import java.util.ArrayList;
import java.util.List;

public class IssueMetricsList {
	private List<MdbMetric> mdbProjects = new ArrayList<>();

	public List<MdbMetric> getMdbProjects() {
		return mdbProjects;
	}

	public void setMdbProjects(List<MdbMetric> mdbProjects) {
		this.mdbProjects = mdbProjects;
	}

}

