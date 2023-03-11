package logic;

public class GuildMember {
	private String name;
	private String jobTitle;
	private String myDepartment;
	private int salary;
	
	public GuildMember(String name, String jobTitle, int salary) {
		//TODO: Value constructor. Instantiates a member with the given values.
	}
	
	public String getName() {
		//TODO: Return member's name here.
		
		return null;
	}
	public void setName(String name) {
		//TODO: Set the member's name. If it's blank, set is as "Anon".
		
	}
	public String getJobTitle() {
		//TODO: Return the member's job title.
		
		return null;
	}
	public void setJobTitle(String jobTitle) {
		//TODO: Set the member's job title. If it's blank, set it as "Adventurer".
		
	}
	public String getMyDepartment() {
		//TODO: Return the member's department.
		
		return null;
	}
	public void setMyDepartment(String myDepartment) {
		//TODO: Set the member's department.
	}
	public int getSalary() {
		//TODO: Get the member's salary.
		
		return 0;
	}
	public void setSalary(int salary) {
		//TODO: Set the member's salary.
		//If it is less than 0, set is as 0.
		//If it is greater than 100000, set is as 100000.
	}
	
	//------------------------------------------
	//---do not edit anything below this line---
	//----but feel free to look at the code!----
	//------------------------------------------
	
	public String toString() {
		return name + " the " + jobTitle + " of the " + myDepartment + " Department (Salary: " + salary + ")";
	}
}
