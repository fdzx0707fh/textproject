package com.jc.zsl.text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class MakeXml {
	private final static String rootPath = "C:\\Users\\zsl\\Desktop\\values\\values-{0}x{1}";
	public static File filePath = new File("C:\\Users\\zsl\\Desktop\\values");
	private final static float dw = 1920f;// 320 1单位长度=1px 640px ,1单位长度=2px
	private final static float dh = 1920f;
	private final static String WTemplate = "<dimen name=\"x{0}\">{1}px</dimen>\n";
	private final static String HTemplate = "<dimen name=\"dp{0}\">{1}px</dimen>\n";

	public static void main(String[] args) {
		filePath.mkdirs();
		makeString(320, 480);
		makeString(1080, 1920);
	}


	private static void makeString(int w, int h) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb.append("<resources>");
		float cellw = w / dw;
		for (int i = 1; i < 1920; i++) {
			sb.append(WTemplate.replace("{0}", i + "").replace("{1}",
					change(cellw * i) + ""));
		}
		sb.append(WTemplate.replace("{0}", "1920").replace("{1}", w + ""));
		sb.append("</resources>");

		StringBuffer sb2 = new StringBuffer();
		sb2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
		sb2.append("<resources>");
		float cellh = h / dh;
		for (int i = 1; i < 1920; i++) {
			sb2.append(HTemplate.replace("{0}", i + "").replace("{1}",
					change(cellh * i) + ""));
		}
		sb2.append(HTemplate.replace("{0}", "1920").replace("{1}", h + ""));
		sb2.append("</resources>");

		String path = rootPath.replace("{0}", h + "").replace("{1}", w + "");
		File rootFile = new File(path);
		if (!rootFile.exists()) {
			rootFile.mkdirs();
		}

		File layxFile = new File(path + "/lay_x.xml");
		File layyFile = new File(path + "/lay_y.xml");
		PrintWriter pw;
		try {
			pw = new PrintWriter(new FileOutputStream(layxFile));
			pw.print(sb.toString());
			pw.close();
			pw = new PrintWriter(new FileOutputStream(layyFile));
			pw.print(sb2.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String change(float f) {
		float b = (float) (Math.round(f * 100))/100;
		
		return b+"";
	}

}
