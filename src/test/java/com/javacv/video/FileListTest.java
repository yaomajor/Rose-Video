package com.javacv.video;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

import java.net.MalformedURLException;

/**
 * @ClassName FileListTest
 * @Description TODO
 * @Author 86133
 * @Date 2020/5/29 15:05
 * @Version 1.0
 **/
public class FileListTest {

    public static void main(String[] args) {
        String url="smb://491341:/save1/201612/测试二维码观看3/";
        SmbFile smbFile;
        try {
            String user = "491341";
            String pass ="09180013wyj!@";
            String sharedFolder="eleemo";
            String path="smb://10.224.230.43/"+sharedFolder+"/";
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("",user, pass);
            smbFile = new SmbFile(path,auth);
//            SmbFileOutputStream smbfos = new SmbFileOutputStream(smbFile);
//            smbfos.write("testing.and writing to a file".getBytes());
//            System.out.println("completed nice !");
//            // smb://userName:passWord@host/path/
//            smbFile = new SmbFile(url);
            if (!smbFile.exists()) {
                System.out.println("no such folder");
            } else {
                SmbFile[] files = smbFile.listFiles();
                for (SmbFile f : files) {
                    System.out.println(f.getName());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }
    }
}
