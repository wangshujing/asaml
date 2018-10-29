package com.saml.model;

import java.util.List;

public class DemandInfo extends Demand{
	private List<DemandInfo> items;

	public DemandInfo(Demand d) {
		this.setAddTime(d.getAddTime());
		this.setDemandInfo(d.getDemandInfo());
		this.setDemandLevel(d.getDemandLevel());
		this.setDemandName(d.getDemandName());
		this.setId(d.getId());
		this.setParentId(d.getParentId());
		this.setProjectId(d.getProjectId());
		this.setState(d.getState());
	}

	public DemandInfo() {

	}

	public List<DemandInfo> getItems() {
		return items;
	}

	public void setItems(List<DemandInfo> items) {
		this.items = items;
	}
}
