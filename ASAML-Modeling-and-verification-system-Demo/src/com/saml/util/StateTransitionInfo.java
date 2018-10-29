package com.saml.util;

import java.util.ArrayList;

import com.saml.util.StateTransitionRule;

public class StateTransitionInfo {
	private ArrayList<StateTransitionRule> stRuleSet;
	private ArrayList<String> states;
	private ArrayList<String> edges;
	
	public StateTransitionInfo(){
		stRuleSet = new ArrayList<StateTransitionRule>();
		states = new ArrayList<String>();
		edges = new ArrayList<String>();
	}
	
	public void clearST(){
		if (stRuleSet.size() > 0) {
			stRuleSet.clear();
			states.clear();
			edges.clear();
		}
	}

	public ArrayList<StateTransitionRule> getStRuleSet() {
		return stRuleSet;
	}

	public void setStRuleSet(ArrayList<StateTransitionRule> stRuleSet) {
		this.stRuleSet = stRuleSet;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public void setStates(ArrayList<String> states) {
		this.states = states;
	}

	public ArrayList<String> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<String> edges) {
		this.edges = edges;
	}
	
	
}
