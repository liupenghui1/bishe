package com.sxn.entity;

public class Idiom {
	private int id;
	private String name;
	private String pronounce;
	private String antonym;
	private String homoionym;
	private String derivation;
	private String example;
	private String explain;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pronounce
	 */
	public String getPronounce() {
		return pronounce;
	}
	/**
	 * @param pronounce the pronounce to set
	 */
	public void setPronounce(String pronounce) {
		this.pronounce = pronounce;
	}
	/**
	 * @return the antonym
	 */
	public String getAntonym() {
		return antonym;
	}
	/**
	 * @param antonym the antonym to set
	 */
	public void setAntonym(String antonym) {
		this.antonym = antonym;
	}
	/**
	 * @return the homoionym
	 */
	public String getHomoionym() {
		return homoionym;
	}
	/**
	 * @param homoionym the homoionym to set
	 */
	public void setHomoionym(String homoionym) {
		this.homoionym = homoionym;
	}
	/**
	 * @return the derivation
	 */
	public String getDerivation() {
		return derivation;
	}
	/**
	 * @param derivation the derivation to set
	 */
	public void setDerivation(String derivation) {
		this.derivation = derivation;
	}
	/**
	 * @return the example
	 */
	public String getexample() {
		return example;
	}
	/**
	 * @param example the example to set
	 */
	public void setexample(String example) {
		this.example = example;
	}
	/**
	 * @return the explain
	 */
	public String getExplain() {
		return explain;
	}
	/**
	 * @param explain the explain to set
	 */
	public void setExplain(String explain) {
		this.explain = explain;
	}
	@Override
	public String toString() {
		return "Idiom [id=" + id + ", name=" + name + ", pronounce=" + pronounce + ", antonym=" + antonym
				+ ", homoionym=" + homoionym + ", derivation=" + derivation + ", example=" + example + ", explain="
				+ explain + "]";
	}

	
	

}
