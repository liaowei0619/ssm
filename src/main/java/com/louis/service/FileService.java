package com.louis.service;

import com.louis.entity.FileTb;
import com.louis.vo.response.NotarizationFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * Created by Administrator on 2016/11/13. FileService
 */
public interface FileService {

	/**
	 * 保存一个sysFile文件
	 * 
	 * @param allFile
	 */
	NotarizationFileVo save(FileTb allFile);

	/**
	 * 存文件
	 * 
	 * @param file
	 *            文件
	 * @param path
	 *            存放路径
	 * @return
	 */
	NotarizationFileVo saveFile(MultipartFile file, String path);

	/**
	 * 多文件上传
	 * 
	 * @param files
	 * @param path
	 * @return
	 */
	public List<NotarizationFileVo> saveFiles(MultipartFile[] files,
											  String path, Integer pictype);
}
