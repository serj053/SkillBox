package org.example;

import java.io.File;
import java.util.List;

public class FindFileInDirectory {
    public void find(File f, List<String> list, String end) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        find(file, list, end);
                    } else {
                        if (file.getName().toLowerCase().endsWith(end)) {
                            list.add(file.getPath());
                        }
                    }
                }
            }
        }
    }

}
