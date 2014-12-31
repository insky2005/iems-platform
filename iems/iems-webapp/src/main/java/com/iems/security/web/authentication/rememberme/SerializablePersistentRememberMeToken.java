package com.iems.security.web.authentication.rememberme;

import java.io.Serializable;
import java.util.Date;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

public class SerializablePersistentRememberMeToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8807404205005569522L;
	
	public SerializablePersistentRememberMeToken() {
		
	}

	public SerializablePersistentRememberMeToken(String username,
			String series, String tokenValue, Date date) {
		this.username = username;
		this.series = series;
		this.tokenValue = tokenValue;
		this.date = date;
	}
	
	private String username;
	private String series;
	private String tokenValue;
	private Date date;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public PersistentRememberMeToken toPersistentRememberMeToken() {
		return new PersistentRememberMeToken(username, series, tokenValue, date);
	}
	
	public static SerializablePersistentRememberMeToken fromPersistentRememberMeToken(PersistentRememberMeToken token) {
		return new SerializablePersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
	}
	
	public String toPlainString() {
		return String.format("%s,%s,%s,%s", username, series, tokenValue, date.toString());
	}
}
