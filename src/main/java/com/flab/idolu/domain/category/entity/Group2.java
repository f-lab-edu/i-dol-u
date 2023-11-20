package com.flab.idolu.domain.category.entity;

import static com.flab.idolu.domain.category.entity.Group1.*;

public enum Group2 {
	TOP(CLOTH),
	OUTER(CLOTH),
	KEYRING(LIFESTYLE),
	STICKER(LIFESTYLE),
	CHEER_STICK(LIFESTYLE),
	PHONE_CASE(LIFESTYLE);

	private Group1 group1;

	Group2(Group1 group1) {
		this.group1 = group1;
	}

	public boolean isGroup2Of(String group1) {
		return this.group1.name().equals(group1);
	}
}
