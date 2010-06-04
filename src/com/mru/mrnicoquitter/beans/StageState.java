package com.mru.mrnicoquitter.beans;

import com.google.gson.Gson;

public class StageState {

	private int logoId;
	private int stageID;		//Identifica el array de Stages
	private String[] subSTGs;
	private int activeSubSTG;		// posicion en el array de Stages ¡¡ De momento son Strings!!
	private String stagePreferencesName;
	

	public StageState() {
		super();
	}
	public StageState(int _logoId, int _stageID, String[] _subSTGs,
			int _activeSubSTG, String _stagePreferencesName) {
		super();
		this.logoId = _logoId;
		this.stageID = _stageID;
		this.subSTGs = _subSTGs;
		this.activeSubSTG = _activeSubSTG;
		this.stagePreferencesName = _stagePreferencesName;
	}
	public int getLogoId() {
		return logoId;
	}
	public void setLogoId(int logoId) {
		this.logoId = logoId;
	}

	public int getStageID() {
		return stageID;
	}
	public void setStageID(int stageID) {
		this.stageID = stageID;
	}
	public String[] getSubSTGs() {
		return subSTGs;
	}
	public void setSubSTGs(String[] subSTGs) {
		this.subSTGs = subSTGs;
	}
	public int getActiveSubSTG() {
		return activeSubSTG;
	}
	public void setActiveSubSTG(int activeSubSTG) {
		this.activeSubSTG = activeSubSTG;
	}
	public String getStagePreferencesName() {
		return stagePreferencesName;
	}
	public void setStagePreferencesName(String stagePreferencesName) {
		this.stagePreferencesName = stagePreferencesName;
	}

}
