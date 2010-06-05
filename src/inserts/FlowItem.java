package inserts;

import java.io.Serializable;


@SuppressWarnings("serial")
public class FlowItem implements Serializable{
	private int id;
	private String name;
	private String json;
	
	public FlowItem() {
		// TODO Auto-generated constructor stub
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

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}



}
