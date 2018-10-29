package com.saml.model;

import java.util.List;

public class DemandAndRelation {
	
	private Integer id;
	
	private String demandName;
	
	private List<DemandRelation> relations;
	
	public DemandAndRelation(Demand d) {
		this.setId(d.getId());
		this.setDemandName(d.getDemandName());
	}

	public DemandAndRelation() {

	}

	public List<DemandRelation> getRelations() {
		return relations;
	}

	public void setRelations(List<DemandRelation> relations) {
		this.relations = relations;
	}
	
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName == null ? null : demandName.trim();
    }
   
}
