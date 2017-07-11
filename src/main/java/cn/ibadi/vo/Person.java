package cn.ibadi.vo;

public class Person {
	public int id;
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
	public String name;
	public Person(int age,String name){
		this.name=name;
		this.id=age;
	}
	
}
