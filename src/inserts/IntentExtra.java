package inserts;

public class IntentExtra {

	private String name;
	private String contents;
	
	
	public IntentExtra() {
		super();
	}
	public IntentExtra(String name, String contents) {
		super();
		this.name = name;
		this.contents = contents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}

}
