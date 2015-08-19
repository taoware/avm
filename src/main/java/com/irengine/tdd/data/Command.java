package com.irengine.tdd.data;

public interface Command {

	String COMMAND_TYPE_INVALID = "00";
	String COMMAND_TYPE_VERIFY = "10";
	String COMMAND_TYPE_AUDIT = "11";
	String COMMAND_TYPE_CANCEL = "12";

}