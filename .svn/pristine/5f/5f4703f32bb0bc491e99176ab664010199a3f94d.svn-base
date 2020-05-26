package com.gpdi.hqplus.common.util;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import java.util.Map;

/**
 * @author: zake
 * @Description: 二维码生成工具类
 * @Date: Created in 16:21 2019-07-03
 **/
@Slf4j
public class QRCodeUtil {
    /**
     * 生成二维码
     * @param text 内容，可以是链接或者文本
     * @param path 生成的二维码位置
     */
    public static void encodeQRCode(String text, String path) {
        encodeQRCode(text, path, null, null, null);
    }

    /**
     * 生成二维码 (图片)
     * @param text 内容，可以是链接或者文本
     * @param path 生成的二维码位置
     * @param width 宽度，默认300
     * @param height 高度，默认300
     * @param format 生成的二维码格式，默认png
     */
    public static void encodeQRCode(String text, String path, Integer width, Integer height, String format) {
        try {

            // 得到文件对象
            File file = new File(path);
            // 判断目标文件所在的目录是否存在
            if(!file.getParentFile().exists()) {
                // 如果目标文件所在的目录不存在，则创建父目录
                log.info("目标文件所在目录不存在，准备创建它！");
                if(!file.getParentFile().mkdirs()) {
                    log.info("创建目标文件所在目录失败！");
                    return;
                }
            }

            // 宽
            if (width == null) {
                width = 300;
            }
            // 高
            if (height == null) {
                height = 300;
            }
            // 图片格式
            if (format == null) {
                format = "png";
            }

            // 设置字符集编码
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            // 生成二维码矩阵
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            // 二维码路径
            Path outputPath = Paths.get(path);
            // 写入文件
            MatrixToImageWriter.writeToPath(bitMatrix, format, outputPath);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    /**
     * 生成二维码(流的形式)
     * @param
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] createZxing(String content) throws Exception {
        //二维码中包含的信息
//        String content = " ";
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 指定编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 指定纠错级别(L--7%,M--15%,Q--25%,H--30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 编码内容,编码类型(这里指定为二维码),生成图片宽度,生成图片高度,设置参数
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "png", bout);
        //监测字节转流
//        ByteToFile(bout.toByteArray());
        byte[] bytes = bout.toByteArray();
        bout.flush();
        bout.close();
        return bytes;
    }

    /**
     * 对二维码图片进行解码
     * @param filePath 二维码路径
     * @return 解码后对内容
     */
    public static JSONObject decodeQRCode(String filePath) {

        try {

            // 读取图片
            BufferedImage image = ImageIO.read(new File(filePath));

            // 多步解析
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

            // 一步到位
            // BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)))

            // 设置字符集编码
            Map<DecodeHintType, String> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");

            // 对图像进行解码
            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
            // 解码内容
            JSONObject content = JSONObject.parseObject(result.getText());

            System.out.println("图片内容：  ");
            System.out.println("content： " + content.toJSONString());
            System.out.println("图片中格式：  ");
            System.out.println("encode： " + result.getBarcodeFormat());

            return content;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    static void ByteToFile(byte[] bytes)throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedImage bi1 =ImageIO.read(bais);
        try {
            //可以是jpg,png,gif格式
            File img = new File("/Users/zake/Documents/firsta.png");
            //不管输出什么格式图片，此处不需改动
            ImageIO.write(bi1, "jpg", img);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            bais.close();
        }
    }
}
