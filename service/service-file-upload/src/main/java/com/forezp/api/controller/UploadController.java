package com.forezp.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.security.acl.LastOwnerException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.forezp.api.entity.Picture;
import com.forezp.api.service.able.PictureService;
import com.forezp.api.util.Notice;
import com.forezp.api.utils.FastDFSClientUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "文件操作")
@RestController
public class UploadController {

    @Autowired
    private FastDFSClientUtil dfsClient;

    @Autowired
    private PictureService pictureService;

    @PostMapping("/upload")
    @ApiOperation("上传文件")
    public Notice fdfsUpload(@RequestParam("file") MultipartFile file) {
        Picture picture = null;
        try {
            picture = dfsClient.uploadFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        picture.setId(new Date().getTime() / 1000);
        picture.setPictureSize(file.getSize() / 1024 / 1024 + "M");
        picture.setPictureName(file.getOriginalFilename());
        pictureService.addPicture(picture);
        return new Notice(HttpStatus.OK, picture, "成功上传文件");
    }

    /**
     * @return
     */
    @GetMapping("/deleteFile")
    @ApiOperation("删除文件")
    public Notice delFile(String id) {
        Picture picture = pictureService.findPictureById(Long.parseLong(id));
        try {
            dfsClient.delFile(picture.getPictureAddressl());
        } catch (Exception e) {
            // 文件不存在报异常 ： com.github.tobato.fastdfs.exception.FdfsServerException: 错误码：2，错误信息：找不到节点或文件
            // e.printStackTrace();
        }
        pictureService.deletePicture(Long.parseLong(id));
        return new Notice(HttpStatus.OK, "成功删除");
    }
}