package com.jegor.bowlingcalc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

abstract class ResultAbstract {
	public String toJson() {
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}
}
