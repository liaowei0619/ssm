package com.louis.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import com.louis.config.MYJZConfig;
import com.louis.entity.FileTb;
import com.louis.mapper.FileTbMapper;
import com.louis.utilTools.MD5Utils;
import com.louis.utilTools.ResourceUtils;
import com.louis.utilTools.UUIDUtils;
import com.louis.vo.response.NotarizationFileVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2016/11/13. FileServiceImpl
 */
@Service(FileServiceImpl.SERVICE_NAME)
public class FileServiceImpl implements FileService {

    public static final String SERVICE_NAME = "com.louis.service.FileServiceImpl";

    @Resource
    private FileTbMapper fileTbMapper;

    @Override
    public NotarizationFileVo save(FileTb fileTb) {

        fileTbMapper.insert(fileTb);

        NotarizationFileVo notarizationFileVo = new NotarizationFileVo();
        BeanUtils.copyProperties(fileTb, notarizationFileVo);

        return notarizationFileVo;
    }

    @Override
    public NotarizationFileVo saveFile(MultipartFile file, String path) {
        NotarizationFileVo notarizationFileVo = null;
        if (!file.isEmpty()) {
            FileTb upLoadFile = null;
            String originalName = file.getOriginalFilename();
            int dotPosition = originalName.indexOf(".");
            String fileType = null;
            String filename = null;
            if (dotPosition != -1) {
                fileType = originalName.substring(
                        originalName.indexOf(".") + 1, originalName.length());
                filename = UUIDUtils.getUUID() + "." + fileType;
            } else {
                filename = UUIDUtils.getUUID();
            }
            File savedFile = new File(path, filename);
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(),
                        savedFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            upLoadFile = new FileTb();
            upLoadFile.setFileId(UUIDUtils.getUUID());
            upLoadFile.setFileName(filename);
            upLoadFile.setFileType(fileType);
            upLoadFile.setFileSize(file.getSize());
            upLoadFile.setCreateDate(new Date());
            upLoadFile.setFileMd5(MD5Utils.encode(savedFile));
            upLoadFile.setFileUrl(ResourceUtils
                    .getProperty(MYJZConfig.URL_PATH)
                    + MYJZConfig.PIC_LOCATION + filename);
            upLoadFile.setRealPath(MYJZConfig.PIC_LOCATION
                    + filename);
            notarizationFileVo = save(upLoadFile);
        }

        return notarizationFileVo;
    }

    @Override
    public List<NotarizationFileVo> saveFiles(MultipartFile[] files,
                                              String path, Integer pictype) {

        List<NotarizationFileVo> allFiles = new ArrayList<NotarizationFileVo>();
        for (MultipartFile file : files) {
            NotarizationFileVo notarizationFileVo = null;
            if (!file.isEmpty()) {
                FileTb upLoadFile = null;
                String originalName = file.getOriginalFilename();
                int dotPosition = originalName.indexOf(".");
                String fileType = null;
                String filename = null;
                if (dotPosition != -1) {
                    fileType = originalName.substring(
                            originalName.indexOf(".") + 1,
                            originalName.length());
                    filename = UUIDUtils.getUUID() + "." + fileType;
                } else {
                    filename = UUIDUtils.getUUID();
                }
                File savedFile = new File(path, filename);
                try {
                    FileUtils.copyInputStreamToFile(file.getInputStream(),
                            savedFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                notarizationFileVo = setFileMethod(file, fileType, filename, savedFile);
                allFiles.add(notarizationFileVo);
            }
        }
        return allFiles;
    }

    private NotarizationFileVo setFileMethod(MultipartFile file, String fileType, String filename, File savedFile) {
        FileTb upLoadFile;
        NotarizationFileVo notarizationFileVo;
        upLoadFile = new FileTb();
        upLoadFile.setFileId(UUIDUtils.getUUID());
        upLoadFile.setFileName(filename);
        upLoadFile.setFileType(fileType);
        upLoadFile.setFileSize(file.getSize());
        upLoadFile.setCreateDate(new Date());
        upLoadFile.setFileMd5(MD5Utils.encode(savedFile));
        upLoadFile.setFileUrl(ResourceUtils
                .getProperty(MYJZConfig.URL_PATH)
                + MYJZConfig.PIC_LOCATION + filename);
        upLoadFile.setRealPath(MYJZConfig.PIC_LOCATION
                + filename);
        notarizationFileVo = save(upLoadFile);
        return notarizationFileVo;
    }
}
