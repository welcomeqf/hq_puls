package com.gpdi.hqplus.pay.service.impl;

import com.gpdi.hqplus.pay.entity.WepayBill;
import com.gpdi.hqplus.pay.mapper.WepayBillMapper;
import com.gpdi.hqplus.pay.service.IAlipayService;
import com.gpdi.hqplus.pay.service.IWepayBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lianghb
 * @since 2019-07-02
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class WepayBillServiceImpl extends ServiceImpl<WepayBillMapper, WepayBill> implements IWepayBillService {

    @Autowired
    private IAlipayService alipayService;

    @Override
    public void getDownloadurl(String billType, String billDate) {
        String downloadUrl = alipayService.downloadQuery(billType, billDate);
        //指定希望保存的文件路径
        String filePath = "/Users/fund_bill_20160405.zip";
        URL url = null;
        HttpURLConnection httpUrlConnection = null;
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            url = new URL(downloadUrl);
            httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setConnectTimeout(5 * 1000);
            httpUrlConnection.setDoInput(true);
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setUseCaches(false);
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setRequestProperty("Charsert", "UTF-8");
            httpUrlConnection.connect();
            fis = httpUrlConnection.getInputStream();
            byte[] temp = new byte[1024];
            int b;
            fos = new FileOutputStream(new File(filePath));
            while ((b = fis.read(temp)) != -1) {
                fos.write(temp, 0, b);
                fos.flush();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (httpUrlConnection != null) {
                    httpUrlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
