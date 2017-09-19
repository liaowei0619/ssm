package com.louis.mapper;

import com.louis.entity.FileTb;
import org.apache.ibatis.annotations.Param;

public interface FileTbMapper {

    int deleteByPrimaryKey(String fileId);

    int insert(FileTb record);

    int insertSelective(@Param("record") FileTb record);

    FileTb selectByPrimaryKey(@Param("fileId") String fileId);

}