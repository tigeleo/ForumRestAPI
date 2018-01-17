package com.theforum.db;

import java.util.Date;

import javax.persistence.Column;

public class EntityTimestamp {
	@Column(name = "created")
	private Date created;

	@Column(name = "updated")
	private Date updated;
	public Date getCreated() {
		return created;
	}






	public void setCreated(Date created) {
		this.created = created;
	}






	public Date getUpdated() {
		return updated;
	}






	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
