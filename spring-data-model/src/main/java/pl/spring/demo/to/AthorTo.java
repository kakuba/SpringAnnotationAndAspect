package pl.spring.demo.to;

public class AthorTo implements IdAware {
	private Long id;
	private String firstName;
	private String lastName;
	
	public AthorTo() {
	}
	
	public AthorTo(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public AthorTo(Long id, String firstName) {
		this.id = id;
		this.firstName = firstName;
	}

	@Override
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
