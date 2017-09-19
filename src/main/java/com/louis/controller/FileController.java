package com.louis.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.louis.config.MYJZConfig;
import com.louis.service.FileService;
import com.louis.service.FileServiceImpl;
import com.louis.utilTools.FilePathUtils;
import com.louis.vo.ResponseTemplate;
import com.louis.vo.response.NotarizationFileVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13. FileController
 */
@RestController
@RequestMapping(path = MYJZConfig.API)
public class FileController {

    @Resource(name = FileServiceImpl.SERVICE_NAME)
    private FileService fileService;

    @RequestMapping(path = "/fileUpload", method = RequestMethod.POST)
    public ResponseTemplate uploadFile(
            @RequestParam(name = "file", required = true) MultipartFile file, HttpServletRequest request) {
        String path = FilePathUtils.buildFilePath(request,
                MYJZConfig.FILE_LOCATION);
        NotarizationFileVo upLoadFile = fileService.saveFile(file, path);
        ResponseTemplate template = new ResponseTemplate(upLoadFile);
        return template;
    }

    /**
     * 多文件上传
     *
     * @param files
     * @param request
     * @return
     */
    @RequestMapping(path = "/fileUploads", method = RequestMethod.POST)
    public ResponseTemplate uploadFiles(
            @RequestParam(name = "files", required = true) MultipartFile[] files,
            Integer pictype, HttpServletRequest request) {

        String path = FilePathUtils.buildFilePath(request,
                MYJZConfig.FILE_LOCATION);
        List<NotarizationFileVo> upLoadFile = fileService.saveFiles(files,
                path, pictype);
        return new ResponseTemplate(upLoadFile);
    }
}
