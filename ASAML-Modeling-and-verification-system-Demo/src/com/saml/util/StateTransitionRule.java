package com.saml.util;

public class StateTransitionRule {
	public String fromState;
	public String events;
	public String toState;
	
	
	
	public StateTransitionRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StateTransitionRule(String fromState, String events, String toState) {
		super();
		this.fromState = fromState;
		this.events = events;
		this.toState = toState;
	}
	public String getFromState() {
		return fromState;
	}
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}
	public String getEvents() {
		return events;
	}
	public void setEvents(String events) {
		this.events = events;
	}
	public String getToState() {
		return toState;
	}
	public void setToState(String toState) {
		this.toState = toState;
	}

	@Override
	public String toString() {
		return "StateTransitionRule [fromState=" + fromState + ", events="
				+ events + ", toState=" + toState + "]";
	}
	
}
