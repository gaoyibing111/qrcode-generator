package cn.rx.service.impl;

import cn.rx.common.util.QREncoder;
import cn.rx.service.IQrcodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;

@Service("QrcodeServiceImpl")
public class QrcodeServiceImpl implements IQrcodeService {

    private Logger logger = LoggerFactory.getLogger(QrcodeServiceImpl.class);

    @Override
    public BufferedImage generateQrcode(String text) {
        long start = System.currentTimeMillis();
        logger.debug("========encoder start...");
        logger.debug("========content length: " + StringUtils.length(text));
        BufferedImage bufferedImage = QREncoder.encoderQRCode(text);
        logger.debug("========encoder success!!!");
        long end = System.currentTimeMillis();
        logger.debug("cost time：" + (end - start) + " ms");
        return bufferedImage;
    }

    @Override
    public File generateQrcode(String text, String path) {
        long start = System.currentTimeMillis();
        logger.debug("========encoder start...");
        logger.debug("========content length: " + StringUtils.length(text));
        boolean result = QREncoder.encoderQRCode(text, path);
        logger.debug("========call encoder success, encode result : " + result);
        long end = System.currentTimeMillis();
        logger.debug("cost time：" + (end - start) + " ms");
        return new File(path);
    }
}
