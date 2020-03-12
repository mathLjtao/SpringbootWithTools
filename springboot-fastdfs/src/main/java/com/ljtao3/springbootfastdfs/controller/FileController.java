package com.ljtao3.springbootfastdfs.controller;

import com.ljtao3.springbootfastdfs.config.FileDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ljtao3 on 2020/3/12
 */
@RestController
@RequestMapping("/file")
@Api(value="fileController",tags = { "文件接口" })
public class FileController {
    @Resource
    private FileDfsUtil fileDfsUtil;
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
    @GetMapping("/deleteByPath")
    public ResponseEntity<String> deleteByPath(){
        String filePathName="";
        fileDfsUtil.deleteFile(filePathName);
        return ResponseEntity.ok("success");
    }
}
