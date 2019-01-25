package com.nick.main.distance.textfile;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.nick.main.distance.DistanceOutput;

public class TextFileDistanceOutput implements DistanceOutput {
	public static final String pathMod = " DistanceCalculated";
	private String outputFilePath;
	private Map<Integer, List<String>> infoByRowNumb;
	private List<String> pointsA;
	private List<String> pointsB;
	
	public TextFileDistanceOutput(TextFileDistanceData dataType) {
		outputFilePath = dataType.getfilePath().replace(".txt", pathMod+".txt");
	}

	@Override
	public void documentOutput() throws Exception {
		List<String> lines = new ArrayList<String>();
		lines.add("pointA"+"\t"+
				  "pointB"+"\t"+
				  "distMiles"+"\t"+
				  "duratSeconds"+"\t"+
				  "duratSecondsInTraffic"+"\t"+
				  "fare");
		for (int rowNumb = 1; rowNumb <= infoByRowNumb.size(); rowNumb++) {
			String pointA = pointsA.get(rowNumb - 1);
			String pointB = pointsB.get(rowNumb - 1);
			List<String> info = infoByRowNumb.get(rowNumb);
			String distMiles = info.get(0);
			String duratSeconds =  info.get(1);
			String duratSecondsInTraffic =  info.get(2);
			String fare =  info.get(3);
			lines.add(pointA+"\t"+
					  pointB+"\t"+
				   	  distMiles+"\t"+
					  duratSeconds+"\t"+
					  duratSecondsInTraffic+"\t"+
					  fare);
		}
		
		Path file = Paths.get(outputFilePath);
		Files.write(file, lines, Charset.forName("UTF-8"));
		JOptionPane.showMessageDialog(null, "Process Complete!");
	}

	@Override
	public void catchException(Exception ex) {
	}

	@Override
	public void setResults(Map<Integer, List<String>> infoByRowNumb,List<String> pointsA, List<String> pointsB) {
		this.infoByRowNumb = infoByRowNumb;
		this.pointsA = pointsA;
		this.pointsB = pointsB;
	}
}
