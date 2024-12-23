package cn.edu.nhic.nhtj.file.controller;


import cn.edu.nhic.nhtj.file.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class FileController {
    @PostMapping
    public Map<String,Object> upload(@RequestParam ("file") MultipartFile file, HttpServletRequest request){
        String tname = new FileUploadUtil().upload(file,request.getServletContext());
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("data",tname);
        return map;
    }
}
