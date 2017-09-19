package com.louis.utilTools;


import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by liuw on 17/1/17.
 * 文件路径问题
 */
public class FilePathUtils {

    /**
     * 得到文件路径
     * @param request
     * @param relativePath
     * @return
     */
    public static String buildFilePath(HttpServletRequest request,String relativePath)
    {
        String path = request.getSession().getServletContext().getRealPath("");
        File location = new File(path);
        if(location.isDirectory())
        {
            path = location.getParent();
        }
        path += relativePath;
        return path;
    }

}
