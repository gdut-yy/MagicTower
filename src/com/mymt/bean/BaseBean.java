package com.mymt.bean;

import com.mymt.MTGame;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * BaseBean 类
 * <p>
 * 作为 DialoguesBean、ItemsBean、MonsterBean 的基类。
 * 根据 id 的值取 对应的资源图片。
 *
 * @author ZYY
 */
public class BaseBean {

    private final static int PIXEL_NUM = 72;    // 像素值。需跟资源文件的像素值相匹配。

    public int getId() {
        return id;
    }

    protected int id;

    public BufferedImage draw() throws IOException {
        if (this instanceof MonsterBean)
            return MTGame.enemyImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
        else if (this instanceof DialoguesBean)
            return MTGame.dialogueImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
        else
            return MTGame.itemImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
    }
}
