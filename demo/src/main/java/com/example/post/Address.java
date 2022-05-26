package com.example.post;

public class Address {
	
    private Long id;
    private String streetAddress;
    private String suite;
    private String city;
    private String zipcode;
    private Coordinates geo;
    
    public Address() {
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Coordinates getGeo() {
		return geo;
	}
	public void setGeo(Coordinates geo) {
		this.geo = geo;
	}


    //getters and setters
}


