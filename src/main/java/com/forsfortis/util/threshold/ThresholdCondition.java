package com.forsfortis.util.threshold;

public enum ThresholdCondition {
	EQUALTO("="),
	GREATERTHAN(">"),
	LESSTHAN("<");
	
	private final String condition;
	
	public String getCondition() {
		return condition;
	}

	ThresholdCondition(String condition){
		this.condition=condition;
	}
}
