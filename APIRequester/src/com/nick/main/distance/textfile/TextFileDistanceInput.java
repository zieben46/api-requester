package com.nick.main.distance.textfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nick.main.distance.DistanceInput;

public class TextFileDistanceInput implements DistanceInput {
	private String filePath;
	private BufferedReader br;
	private List<String> point1 = new ArrayList<>();
	private List<String> point2= new ArrayList<>();

	public TextFileDistanceInput(TextFileDistanceData dataType) {
		this.filePath = dataType.getfilePath();
	}

	@Override
	public void retrieveInput() throws Exception {
		br = new BufferedReader(new FileReader(filePath));
		String currLine;
		while ((currLine = br.readLine()) != null) {
			String[] points = currLine.split("\t");
			if (points.length == 2) {
				point1.add(points[0]);
				point2.add(points[1]);
			}
		}
		br.close();
	}

	@Override
	public void catchException(Exception ex) {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//catch other exceptions
	}


	@Override
	public List<List<String>> getInput() {
		return Arrays.asList(point1, point2);
	}
}


