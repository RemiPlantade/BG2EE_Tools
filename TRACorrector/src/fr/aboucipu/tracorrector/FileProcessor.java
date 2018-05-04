package fr.aboucipu.tracorrector;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileProcessor {
	/**
	 * [@][\d][\d]*[ ]?[=].*
	 * 
	 */
	// Collection des repliques identifiées par leurs 
	private HashMap<String,String> origin_replicas = new HashMap<>();
	private List<Integer> duplReplicas = new ArrayList<Integer>();

	private Stream<String> lines;

	public FileProcessor() {
		readOriginFile();
	}

	private String getIdOfReplica(String line) {
		Pattern p = Pattern.compile("[@][\\d][\\d]*");
		Matcher m = p.matcher(line);
	    // if an occurrence if a pattern was found in a given string...
	    if (m.find()) {
	       return m.group(0);
	    }
		return null;
	}

	private void readOriginFile() {
		System.out.println("Start read origin file");
		try (Stream<String> lines = Files.lines(Paths.get("dialogEn.tra"))) {			
			AtomicReference<String> previousId  = new AtomicReference<String>("");
		    lines.forEach(line ->{
				String id =  getIdOfReplica(line);
				if(id != null) {
					origin_replicas.put(id,line);
					previousId.set(id);
				}else {
					String text= origin_replicas.get(previousId.get());
					text += "\n" + line; 
					origin_replicas.put(previousId.get(),text);
				}
		    });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("End read origin file");
		System.out.println("Number of replica : " + origin_replicas.size());
	}

	public void process(String string) throws IOException {
		lines = loadFile(string);	
		try (Stream<String> lines = Files.lines(Paths.get("dialogEn.tra"))) {			
			AtomicReference<String> previousId  = new AtomicReference<String>("");
		    lines.forEach(line ->{
				String id =  getIdOfReplica(line);
				if(id != null) {
					origin_replicas.put(id,line);
					previousId.set(id);
				}else {
					String text= origin_replicas.get(previousId.get());
					text += "\n" + line; 
					origin_replicas.put(previousId.get(),text);
				}
		    });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Stream<String> loadFile(String filePath) throws IOException {
		return Files.lines(Paths.get(filePath));
	}

}
