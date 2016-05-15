package cn.rx.qrcode.controller;

import cn.rx.common.util.MD5Util;
import cn.rx.service.IQrcodeService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.OutputStream;

/**
 * 二维码服务
 * <p/>
 * 创建时间: 16/5/15<br/>
 *
 * @author xule
 * @since v0.0.1
 */
@Controller
@RequestMapping(value = "/qr")
public class QrcodeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(QrcodeController.class);

    @Autowired
    private IQrcodeService qrcodeService;

    /**
     * 根据文本生成二维码
     * @return
     */
    @RequestMapping(value = "/create", method = {RequestMethod.GET})

    public void createQrcodeByText(HttpServletResponse response, String text) {
        try {
            String randomStr = StringUtils.join(System.currentTimeMillis(), Math.random());
            String filename = MD5Util.digest(randomStr) + ".png";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/octet-stream");//APPLICATION/OCTET-STREAM
            String attachFilename = new String(filename.getBytes("GBK"), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=\"" + attachFilename + "\"");
            //response.setContentLength((int)text.length());

            OutputStream os = new BufferedOutputStream(response.getOutputStream());

            BufferedImage bufImg = qrcodeService.generateQrcode(text);
            ImageIO.write(bufImg, "png", os);
//            os.write(data);

            response.setStatus(response.SC_OK);
            response.flushBuffer();
            os.flush();
        }catch (Exception e){
            logger.error("", e);
        }
    }
}
