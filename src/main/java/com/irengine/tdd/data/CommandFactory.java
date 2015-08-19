package com.irengine.tdd.data;

public class CommandFactory {
	
	public static RequestCommand CreateCommand(String data) {
		
		String type = data.substring(0, 2);
		
		switch (type) {
		case Command.COMMAND_TYPE_VERIFY:
			return new VerifyRequestCommand(data);
		case Command.COMMAND_TYPE_AUDIT:
			return new AuditRequestCommand(data);
		case Command.COMMAND_TYPE_CANCEL:
			return new CancelRequestCommand(data);
		default:
			return null;
		}

	}

}
