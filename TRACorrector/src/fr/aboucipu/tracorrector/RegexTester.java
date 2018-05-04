package fr.aboucipu.tracorrector;

import org.apache.commons.lang3.StringUtils;

public class RegexTester {

	public static void main(String[] args) {
		String line = "@numero = ~texte masculin~ [SON] ~texte féminin~ [SON]";
		int idxSecond = StringUtils.ordinalIndexOf(line, "~", 2);
		int idxThird = StringUtils.ordinalIndexOf(line, "~", 3);
		int idxFourth = StringUtils.ordinalIndexOf(line, "~", 4);
		
		if(idxSecond != -1) {
			if(idxThird != -1) {
				System.out.println(line.substring(idxSecond+1, idxThird));
			}else {
				System.out.println(line.substring(idxSecond+1,  line.length()));
			}
			if(idxFourth != -1) {
				System.out.println(line.substring(idxFourth+1, line.length()));
			}
		}
		
//		Pattern p = Pattern.compile("[@][\\d][\\d]*");
//		Matcher m = p.matcher(line);
//	    // if an occurrence if a pattern was found in a given string...
//	    if (m.find()) {
//	       return m.group(0);
//	    }

	}

}
