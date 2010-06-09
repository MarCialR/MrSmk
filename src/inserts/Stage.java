package inserts;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

@SuppressWarnings("serial")
public class Stage implements Serializable{
	private String json;
	
	private int id;
	private String name;
	private String activity;
	private transient List<IntentExtra> extras;
	private IntentExtra[] extrasArray;
	
	public void convert(){
		if (null == extras || extras.size()== 0){
			extrasArray = new IntentExtra[1];
		} else 
			extras.toArray(extrasArray);
	}
	public Stage() {
		extras = new ArrayList<IntentExtra>();
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public List<IntentExtra> getExtras() {
		return extras;
	}

	public void setExtras(List<IntentExtra> extras) {
		this.extras = extras;
	}

	public void addExtra(IntentExtra extra){
		this.extras.add(extra);
	}

	public IntentExtra[] getExtrasArray() {
		return extrasArray;
	}

	public void setExtrasArray(IntentExtra[] extrasArray) {
		this.extrasArray = extrasArray;
	}



}
