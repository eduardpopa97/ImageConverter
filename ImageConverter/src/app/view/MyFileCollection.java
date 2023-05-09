package app.view;

public class MyFileCollection {

    private String fileName = null;    // numele fisierului
    private long fileSize = 0;		   // dimensiunea fisierului

    public MyFileCollection() {
    }

    public MyFileCollection(String fileName, long fileSize) {  // setter pentru cele 2 variabile
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public String getFileName() {   // getter pentru numele fisierului
        return fileName;
    }

    public void setFileName(String fileName) {  // setter pentru numele fisierului
        this.fileName = fileName;
    }

    public long getFileSize() {   // getter pentru dimensiunea fisierului
        return fileSize;
    }

    public void setFileSize(long fileSize) {   // setter pentru dimensiunea fisierului
        this.fileSize = fileSize;
    }
}