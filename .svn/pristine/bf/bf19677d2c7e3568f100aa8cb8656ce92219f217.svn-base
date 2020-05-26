package com.gpdi.hqplus.file.util;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @ClassName:FastDFSClientWrapper
 * @Description:FastDFS包装器
 * @author:lianghb
 * @date:2019/1/31 13:38
 **/
@Component
public class FastDFSClientWrapper {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${fdfs.url}")
    private String fileURl;

    @Value("${fdfs.maxFileSize}")
    private Integer fileSize;

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadFile(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadFile((InputStream) file.getInputStream(), file.getSize(), FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    private String getResAccessUrl(StorePath storePath) {
        String file = "/" + storePath.getFullPath();
        return file;
    }

    public String getURL() {
        return this.fileURl;
    }

    /**
     * 将一段字符串生成一个文件上传
     *
     * @param content       文件内容
     * @param fileExtension
     * @return
     */
    public String uploadFile(String content, String fileExtension) {
        byte[] buff = content.getBytes(Charset.forName("UTF-8"));
        ByteArrayInputStream stream = new ByteArrayInputStream(buff);
        StorePath storePath = fastFileStorageClient.uploadFile(stream, buff.length, fileExtension, null);
        return getResAccessUrl(storePath);
    }

    public String uploadFile(byte[] buff, String fileExtension) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(buff);
        StorePath storePath = fastFileStorageClient.uploadFile(inputStream, buff.length, fileExtension, null);
        return getResAccessUrl(storePath);
    }

    /**
     * 传图片并同时生成一个缩略图 "JPG", "JPEG", "PNG", "GIF", "BMP", "WBMP"
     *
     * @param file 文件对象
     * @return 文件访问地址
     * @throws IOException
     */
    public String uploadImageAndCrtThumbImage(MultipartFile file) throws IOException {
        StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
                FilenameUtils.getExtension(file.getOriginalFilename()), null);
        return getResAccessUrl(storePath);
    }

    /**
     * 删除文件
     *
     * @param fileUrl 文件访问地址
     * @return
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        try {
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            fastFileStorageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (FdfsUnsupportStorePathException e) {
            e.getMessage();
        }
    }

    /**
     * 下载文件
     *
     * @param fileUrl 文件URL
     * @return 文件字节
     * @throws IOException
     */
    public byte[] downloadFile(String fileUrl) throws IOException {
        int g = fileUrl.indexOf("g");
        int m = fileUrl.indexOf("M");
        String group = fileUrl.substring(g, m);
        group = group.substring(0, group.length() - 1);
        String path = fileUrl.substring(m);
        //      要截取这个  path = "M00/00/00/rB4AB1wklPWAZfogABq45VfPLQI235.jpg";
        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = fastFileStorageClient.downloadFile(group, path, downloadByteArray);
        return bytes;
    }
}
