package com.ljtao3.springbootfastdfs.controller;

import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.ljtao3.springbootfastdfs.config.FileDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author ljtao3 on 2020/3/12
 */
@RestController
@RequestMapping("/file")
@Api(value="fileController",tags = { "文件接口" })
public class FileController {
    @Resource
    private FileDfsUtil fileDfsUtil;
    @Resource
    private FastFileStorageClient storageClient;
    /*
        http://localhost:9014/swagger-ui.html
        将返回的路径加上ip地址，就可以访问到上传的文件
        http://192.168.23.129/group1/M00/00/00/wKhIgl0n4AKABxQEABhlMYw_3Lo825.png
     */
    @ApiOperation(value="上次文件",notes = "测试FastDFS文件上传")
    @RequestMapping(value = "/uploadFile",headers="content-type=multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<String> updateFile(@RequestParam("file")MultipartFile file){
        String result;
        try {
            String path=fileDfsUtil.upload(file);
            if(!StringUtils.isEmpty(path)){
                result=path;
            }
            else{
                result="上次失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result="服务异常";
        }
        return ResponseEntity.ok(result);
    }

    /*
        官方FastDFS_Client框架说明：
        https://github.com/tobato/FastDFS_Client
        下载单个文件
        https://www.jianshu.com/p/f440e495f309
        // 获取文件元数据
        Set<MateData> getMetadata(String groupName, String path);
     */
    @RequestMapping("/downloadFile/{id}")
    public ResponseEntity<String> downloadFile(@PathVariable String id, HttpServletResponse response) throws IOException {
        String filePath="";
        String fileName= URLEncoder.encode("测试.txt","UTF8");
        //DownloadByteArray callback=new DownloadByteArray();
        //byte[] b = storageClient.downloadFile("gourp1", filePath, callback);

        byte[] b=new byte[]{'a','b','c',94,95};
        response.reset();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition" ,"attachment;filename=\"" +fileName+ "\"");
        response.getOutputStream().write(b);
        response.getOutputStream().close();
        return ResponseEntity.ok("");
    }
    /*
    第二种下载文件做法
     */
    @RequestMapping("/downloadFile2/{id}")
    public ResponseEntity<String> downloadFile2(@PathVariable String id, HttpServletResponse response) throws IOException {
        String filePath="测试.txt";
        int i = filePath.lastIndexOf("/")+1;
        String substring = filePath.substring(i);


        String fileName= URLEncoder.encode("测试","UTF8");

        byte[] b=new byte[]{'a','b','c',94,95,'9',9};

        String fileSuffixName=filePath.substring(filePath.lastIndexOf(".")+1);
        response.reset(); //清除缓存
        response.setContentType("application/" +fileSuffixName + ";" +"charset = UTF-8"); //设置字符集和文件后缀名
        response.setHeader("Content-Disposition","attachment; filename=" +fileName+"."+fileSuffixName); // 设置文件名称
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(b);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();

        return ResponseEntity.ok("");
    }



    @GetMapping("/deleteByPath")
    public ResponseEntity<String> deleteByPath(String fileName){
        String filePathName=fileName;
        fileDfsUtil.deleteFile(filePathName);
        return ResponseEntity.ok("success");
    }
}
