package com.gpdi.hqplus.file.manager;

import com.gpdi.hqplus.common.manager.FileService;
import com.gpdi.hqplus.common.util.ExceptionUtil;
import com.gpdi.hqplus.common.util.StringUtil;
import com.gpdi.hqplus.file.util.FastDFSClientWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lianghb
 * @date ：Created in 2019/3/8 17:17
 */
@Service
public class FileServiceImpl implements FileService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FastDFSClientWrapper fastDFSClientWrapper;

    @Override
    public byte[] download(String path) {
        if (StringUtil.isBlank(path)) {
            return null;
        }
        try {
            return fastDFSClientWrapper.downloadFile(path);
        } catch (Exception e) {
            log.error("download file fail！url:{},info:{}", path, ExceptionUtil.getMassage(e));
        }
        return null;
    }

    @Override
    public String upload(String fileName, byte[] file) {
        for (int i = 0; i < 3; i++) {
            try {
                return fastDFSClientWrapper.uploadFile(file, fileName);
            } catch (Exception e) {
                log.error("upload file fail！url:{},info:{}", fileName, ExceptionUtil.getMassage(e));
            }
        }
        return null;
    }

    @Override
    public boolean delete(String path) {
        try {
            fastDFSClientWrapper.deleteFile(path);
            return true;
        } catch (Exception e) {
            log.error("delete file fail！url:{},info:{}", path, ExceptionUtil.getMassage(e));
        }
        return false;
    }
}
