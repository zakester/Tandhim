package com.example.tandhim.Models;


import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;
import org.jdatepicker.JDatePicker;

public class Test2 {
    public static void main(String args[]){
   Test2 t=new Test2();
   t.getdisk();
    }
    public String getdisk(){
        List <File>files = Arrays.asList(File.listRoots());
        String disk = "";
      for (File f : files) {
        String s1 = FileSystemView.getFileSystemView().getSystemDisplayName (f);
        String s2 = FileSystemView.getFileSystemView().getSystemTypeDescription(f);
        File diskPartition = new File(s1);
 
        long totalCapacity = diskPartition.getTotalSpace(); 
 
        long freePartitionSpace = diskPartition.getFreeSpace(); 
        long usablePatitionSpace = diskPartition.getUsableSpace(); 
        
        System.out.println("getSystemDisplayName : " + s1+" Total cap "+totalCapacity+" free cap "+freePartitionSpace+" usable cap "+usablePatitionSpace);
        System.out.println("getSystemTypeDescription : " + s2);
        if (s2.contentEquals("Disque local")){
        disk=disk+s1+"  ";}
      }
   
       System.out.println(disk);
       return disk;
   
}
 protected ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}
  
    
}