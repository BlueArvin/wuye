package wuye.manager.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
	
	public static List<FileBean> traverseFolder2(String path) {
        
		List<FileBean> list = new ArrayList<FileBean>();
		File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
            } else {
                for (File file2 : files) {
                	FileBean fb = new FileBean();
                	if (file2.isDirectory()) {
                        System.out.println("文件夹:" + file2.getAbsolutePath());
                        fb.setType(0);
                        fb.setName(file2.getName());
                        List<FileBean> chList = traverseFolder2(file2.getAbsolutePath());
                        fb.setFbList(chList);
                    } else {
                        System.out.println("文件:" + file2.getAbsolutePath());
                        fb.setType(1);
                        fb.setName(file2.getName());
                    }
                	list.add(fb);
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        
        return list;
    }
}
