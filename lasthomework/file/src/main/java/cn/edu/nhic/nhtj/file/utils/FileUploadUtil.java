/**
 * FileName: FileUploadUtil
 * Author:   liuws
 * Date:     2021/4/26 19:29
 */
package cn.edu.nhic.nhtj.file.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileUploadUtil {
    private  String uploadPath = "/uploads/"; // 存在路径 .jpg .gif .png
    private  String[] fileType = new String[]{"image/jpeg", "image/gif", "image/png", "application/x-bmp"};// 上传文件类型
    private String serverPath;
    private void init(ServletContext servletContext){
        serverPath = servletContext.getRealPath(uploadPath);
        System.out.println(serverPath);
        File filePath = new File(serverPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
    }
    public List<String> upload(List<MultipartFile> files, ServletContext servletContext){
        List<String> paths=new ArrayList<String>();
        if(!files.isEmpty()&&files.size()>0){
            init(servletContext);
            for (MultipartFile file:files){
                String fileName=save(file);
                if(fileName!=null){
                    paths.add(fileName);
                }
            }
            return paths;
        }
        return null;
    }
    public  String upload(MultipartFile uFile, ServletContext servletContext) {
        if (!uFile.getOriginalFilename().isEmpty()) {
            init(servletContext);
            return save(uFile);
        }
        return null;
    }
    public void deleteFile(String fileName, ServletContext servletContext) {
        serverPath = servletContext.getRealPath(uploadPath);
        File file = new File(serverPath + fileName);
        file.delete();
    }
    private String save(MultipartFile uFile){
        if(!checkFileType(uFile.getContentType())){
            System.out.println("类型不允许");
            return null;
        }
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + getFileTypeName(uFile.getOriginalFilename());
        String realPath = serverPath + fileName;
        //System.out.println(realPath);
        try {
            uFile.transferTo(new File(realPath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }


    private  String getFileTypeName(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    private  boolean checkFileType(String inFileType) {
        boolean result = true;
        //String typeName = getFileTypeName(iniFileName);
        for (String type:fileType){
            if(type.equalsIgnoreCase(inFileType)){
                return true;
            }
        }
       return false;
    }
    public  Map<String,String> createFile(String fileExt, ServletContext servletContext) throws Exception {
        init(servletContext);
        HashMap<String, String> path = new HashMap<>();
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid +fileExt;
        path.put("fileName",uploadPath+fileName);
        path.put("fullPath",serverPath+fileName);
        return path;
    }
}
