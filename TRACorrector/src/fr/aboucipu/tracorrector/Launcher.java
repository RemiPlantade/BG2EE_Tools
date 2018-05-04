package fr.aboucipu.tracorrector;

import java.io.IOException;

public class Launcher {

	public static void main(String[] args) {
		FileProcessor processor = new FileProcessor();
		if(args.length > 1 && args[0].equals("console")) {
			for (int i = 1 ; i < args.length ; i++) {
				try {
					processor.process(args[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}else {
			// Run graphical interface
		}

	}

}
