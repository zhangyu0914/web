package com.yw.common.beansUtils.utils.mail;


public class EmailAttachFile
{
    private String fileName;
    
    private String filepath;
    
    private boolean isImage;
   
    

    public boolean isImage()
    {
        return isImage;
    }

    public void setImage(boolean isImage)
    {
        this.isImage = isImage;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFilepath()
    {
        return filepath;
    }

    public void setFilepath(String filepath)
    {
        this.filepath = filepath;
    }
}
