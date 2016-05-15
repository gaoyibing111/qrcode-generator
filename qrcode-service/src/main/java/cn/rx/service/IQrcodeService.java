package cn.rx.service;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 二维码生成
 * <p/>
 * 创建时间: 16/5/15<br/>
 *
 * @author xule
 * @since v0.0.1
 */
public interface IQrcodeService {

    /**
     * 生成文本的二维码
     * @param text
     * @return
     */
    BufferedImage generateQrcode(String text);

    /**
     * 生成二维码文件
     * @param text
     * @param path
     * @return
     */
    File generateQrcode(String text, String path);
}
