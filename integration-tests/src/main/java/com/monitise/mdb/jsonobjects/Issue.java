package com.monitise.mdb.jsonobjects;

public class Issue {

	private final int total;
	private final Severity severity;

	public Issue(int total, Severity severity) {
		this.total = total;
		this.severity = severity;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return false;
		}
		if (!(obj instanceof Issue)) {
			return false;
		}
		Issue issue2 = (Issue) obj;
		return this.severity == issue2.severity && this.total == issue2.total;
	}
	
	@Override
	public int hashCode(){
		return total + severity.hashCode();
	}
	
	public String toString(){
		return "{total:'"+total+"',severity:'"+severity+"'}";
	}

}
