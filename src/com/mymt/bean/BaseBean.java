package com.mymt.bean;

import com.mymt.MTGame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
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

    //private final static int PIXEL_NUM = 72;    // 像素值。需跟资源文件的像素值相匹配。
//    protected int id;
//
//    public int getId() {
//        return id;
//    }

//    public BufferedImage draw() throws IOException {
//        if (this instanceof MonsterBean) {          // 怪物
//            //return MTGame.enemyImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
//            //return ImageIO.read(new File(System.getProperty("user.dir") + "/res/monster0/" + id + ".png"));
//        } else if (this instanceof DialoguesBean) { // NPC
//            //return MTGame.dialogueImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
//            return ImageIO.read(new File(System.getProperty("user.dir") + "/res/npc0/" + id + ".png"));
//        } else {        // 道具
//            //return MTGame.itemImgSheet.getSubimage(PIXEL_NUM * id, 0, PIXEL_NUM, PIXEL_NUM);
//            //return ImageIO.read(new File(System.getProperty("user.dir") + "/res/monster0/" + id + ".png"));
//        }
//        return null;
//    }
}
