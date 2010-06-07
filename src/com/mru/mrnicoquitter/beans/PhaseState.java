package com.mru.mrnicoquitter.beans;

public class PhaseState {

	private int logoId;
	private int id; 		// Identifica el array de Stages
	private int activeStageCode;
	private String phasePreferencesName;

	public PhaseState() {
		super();
	}

	public PhaseState(int logoId, int id, String phasePreferencesName, int activeStageCode) {
		super();
		this.logoId 				= logoId;
		this.id 					= id;
		this.activeStageCode 		= activeStageCode;
		this.phasePreferencesName 	= phasePreferencesName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getActiveStageCode() {
		return activeStageCode;
	}

	public void setActiveStageCode(int activeStageCode) {
		this.activeStageCode = activeStageCode;
	}

	public int getLogoId() {
		return logoId;
	}

	public void setLogoId(int logoId) {
		this.logoId = logoId;
	}


	public String getPhasePreferencesName() {
		return phasePreferencesName;
	}

	public void setPhasePreferencesName(String stagePreferencesName) {
		this.phasePreferencesName = stagePreferencesName;
	}

}
