package com.irengine.tdd.data;

public class CommandFactory {
	
	public static Command CreateCommand(String data) {
		
		String type = data.substring(0, 2);
		
		switch (type) {
		case Command.COMMAND_TYPE_VERIFY:
			return new VerifyCommand(data);
		case Command.COMMAND_TYPE_AUDIT:
			return new AuditCommand(data);
		case Command.COMMAND_TYPE_CANCEL:
			return new CancelCommand(data);
		default:
			return null;
		}

	}

}
