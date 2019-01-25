package com.nick.main;

public class Master {

	public static void main(String[] args) {
		StorageManager.load();
		new APIChooserGUI();
	}

	public static void begin(final DataType dataType, final String apiKey) {
		Input input = Input.createInput(dataType);
		Output output = Output.createOutput(dataType);
		IOProcessor ioProcessor = IOProcessor.createProcessor(dataType, apiKey, input, output);
		try {
			input.retrieveInput();
			ioProcessor.process();
			output.documentOutput();
		} catch (Exception ex) {
			input.catchException(ex);
			ioProcessor.catchException(ex);
			output.catchException(ex);
			System.out.println(ex);
		}
		restart();
	}
	
	public static void restart() {
		new APIChooserGUI();
	}
}