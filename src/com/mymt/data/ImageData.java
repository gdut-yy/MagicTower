package com.mymt.data;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;


/**
 * ImageData 类
 * <p>
 * 加载资源文件。因为游戏中的 资源文件 需大量重用。
 * 针对这一点进行优化。
 * <p>
 * 具体对应图形 请详见
 * /res/map0
 * /res/map1
 * /res/player
 * /res
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class ImageData {

    private static final int PLAYER_LEFT = -1;
    private static final int PLAYER_DOWN = -2;
    private static final int PLAYER_RIGHT = -3;
    private static final int PLAYER_UP = -4;

    public static HashMap<Integer, BufferedImage> playerMap = new HashMap<>();
    public static HashMap<Integer, BufferedImage> imagesMap0 = new HashMap<>();
    public static HashMap<Integer, BufferedImage> imagesMap1 = new HashMap<>();
    public static BufferedImage gameBgImg;
    public static BufferedImage blankBgImg;
    public static BufferedImage battleBgImg;

    static {
        try {
            gameBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/GameBg.png"));
            blankBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/BlankBg.png"));
            battleBgImg=ImageIO.read(new File(System.getProperty("user.dir") + "/res/BattleBg.png"));

            //
            imagesMap0.clear();
            imagesMap0.put(0, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/0.png")));
            imagesMap0.put(1, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/1.png")));
            imagesMap0.put(2, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/2.png")));
            imagesMap0.put(3, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/3.png")));
            imagesMap0.put(4, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/4.png")));
            imagesMap0.put(5, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/5.png")));
            imagesMap0.put(6, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/6.png")));
            imagesMap0.put(7, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/7.png")));
            imagesMap0.put(8, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/8.png")));
            imagesMap0.put(9, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/9.png")));
            imagesMap0.put(10, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/10.png")));
            imagesMap0.put(11, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/11.png")));
            imagesMap0.put(12, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/12.png")));
            imagesMap0.put(13, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/13.png")));
            imagesMap0.put(14, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/14.png")));
            imagesMap0.put(15, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/15.png")));
            imagesMap0.put(19, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/19.png")));
            imagesMap0.put(20, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/20.png")));
            imagesMap0.put(21, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/21.png")));
            imagesMap0.put(22, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/22.png")));
            imagesMap0.put(23, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/23.png")));
            imagesMap0.put(24, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/24.png")));
            imagesMap0.put(25, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/25.png")));
            imagesMap0.put(26, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/26.png")));
            imagesMap0.put(27, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/27.png")));
            imagesMap0.put(28, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/28.png")));
//            imagesMap0.put(29, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/29.png")));
            imagesMap0.put(30, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/30.png")));
            imagesMap0.put(31, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/31.png")));
            imagesMap0.put(32, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/32.png")));
            imagesMap0.put(33, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/33.png")));
            imagesMap0.put(34, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/34.png")));
            imagesMap0.put(35, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/35.png")));
            imagesMap0.put(36, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/36.png")));
            imagesMap0.put(38, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/38.png")));
            imagesMap0.put(39, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/39.png")));
            imagesMap0.put(40, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/40.png")));
            imagesMap0.put(41, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/41.png")));
            imagesMap0.put(42, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/42.png")));
            imagesMap0.put(43, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/43.png")));
            imagesMap0.put(44, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/44.png")));
            imagesMap0.put(45, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/45.png")));
            imagesMap0.put(46, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/46.png")));
            imagesMap0.put(47, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/47.png")));
            imagesMap0.put(48, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/48.png")));
            imagesMap0.put(49, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/49.png")));
            imagesMap0.put(50, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/50.png")));
            imagesMap0.put(51, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/51.png")));
            imagesMap0.put(52, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/52.png")));
            imagesMap0.put(53, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/53.png")));
            imagesMap0.put(54, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/54.png")));
            imagesMap0.put(55, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/55.png")));
            imagesMap0.put(56, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/56.png")));
            imagesMap0.put(57, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/57.png")));
            imagesMap0.put(58, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/58.png")));
            imagesMap0.put(59, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/59.png")));
            imagesMap0.put(60, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/60.png")));
            imagesMap0.put(61, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/61.png")));
            imagesMap0.put(62, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/62.png")));
            imagesMap0.put(63, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/63.png")));
            imagesMap0.put(64, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/64.png")));
            imagesMap0.put(65, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/65.png")));
            imagesMap0.put(66, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/66.png")));
            imagesMap0.put(67, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/67.png")));
            imagesMap0.put(68, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/68.png")));
            imagesMap0.put(69, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/69.png")));
            imagesMap0.put(70, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/70.png")));
            imagesMap0.put(71, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/71.png")));
            imagesMap0.put(73, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/73.png")));
            imagesMap0.put(75, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/75.png")));
            imagesMap0.put(76, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/76.png")));
            imagesMap0.put(78, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/78.png")));
            imagesMap0.put(80, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/80.png")));
            imagesMap0.put(181, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/181.png")));
            imagesMap0.put(182, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/182.png")));
            imagesMap0.put(183, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/183.png")));
            imagesMap0.put(184, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/184.png")));
            imagesMap0.put(185, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/185.png")));
            imagesMap0.put(186, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/186.png")));
            imagesMap0.put(187, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/187.png")));
            imagesMap0.put(188, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/188.png")));
            imagesMap0.put(189, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/189.png")));
            imagesMap0.put(191, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/191.png")));
            imagesMap0.put(192, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/192.png")));
            imagesMap0.put(193, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/193.png")));
            imagesMap0.put(194, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/194.png")));
            imagesMap0.put(195, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/195.png")));
            imagesMap0.put(196, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/196.png")));
            imagesMap0.put(197, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/197.png")));
            imagesMap0.put(198, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/198.png")));
            imagesMap0.put(199, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/199.png")));
//            imagesMap0.put(201, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/201.png")));
            imagesMap0.put(202, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/202.png")));
            imagesMap0.put(203, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/203.png")));
            // 重复图片
            imagesMap0.put(115, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/15.png")));
            imagesMap0.put(119, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/0.png")));
            imagesMap0.put(129, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/0.png")));
            imagesMap0.put(301, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/13.png")));
            imagesMap0.put(302, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/13.png")));
            imagesMap0.put(303, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/14.png")));
            imagesMap0.put(304, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/14.png")));
            imagesMap0.put(305, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map0/5.png")));

            // 玩家 控制图片
            playerMap.clear();
            playerMap.put(PLAYER_LEFT, ImageIO.read(new File(System.getProperty("user.dir") + "/res/player/left.png")));
            playerMap.put(PLAYER_DOWN, ImageIO.read(new File(System.getProperty("user.dir") + "/res/player/down.png")));
            playerMap.put(PLAYER_RIGHT, ImageIO.read(new File(System.getProperty("user.dir") + "/res/player/right.png")));
            playerMap.put(PLAYER_UP, ImageIO.read(new File(System.getProperty("user.dir") + "/res/player/up.png")));

            // 第二帧
            imagesMap1.clear();
            imagesMap1.put(0, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/0.png")));
            imagesMap1.put(1, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/1.png")));
            imagesMap1.put(2, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/2.png")));
            imagesMap1.put(3, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/3.png")));
            imagesMap1.put(4, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/4.png")));
            imagesMap1.put(5, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/5.png")));
            imagesMap1.put(6, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/6.png")));
            imagesMap1.put(7, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/7.png")));
            imagesMap1.put(8, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/8.png")));
            imagesMap1.put(9, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/9.png")));
            imagesMap1.put(10, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/10.png")));
            imagesMap1.put(11, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/11.png")));
            imagesMap1.put(12, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/12.png")));
            imagesMap1.put(13, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/13.png")));
            imagesMap1.put(14, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/14.png")));
            imagesMap1.put(15, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/15.png")));
            imagesMap1.put(19, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/19.png")));
            imagesMap1.put(20, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/20.png")));
            imagesMap1.put(21, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/21.png")));
            imagesMap1.put(22, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/22.png")));
            imagesMap1.put(23, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/23.png")));
            imagesMap1.put(24, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/24.png")));
            imagesMap1.put(25, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/25.png")));
            imagesMap1.put(26, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/26.png")));
            imagesMap1.put(27, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/27.png")));
            imagesMap1.put(28, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/28.png")));
//            imagesMap1.put(29, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/29.png")));
            imagesMap1.put(30, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/30.png")));
            imagesMap1.put(31, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/31.png")));
            imagesMap1.put(32, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/32.png")));
            imagesMap1.put(33, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/33.png")));
            imagesMap1.put(34, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/34.png")));
            imagesMap1.put(35, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/35.png")));
            imagesMap1.put(36, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/36.png")));
            imagesMap1.put(38, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/38.png")));
            imagesMap1.put(39, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/39.png")));
            imagesMap1.put(40, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/40.png")));
            imagesMap1.put(41, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/41.png")));
            imagesMap1.put(42, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/42.png")));
            imagesMap1.put(43, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/43.png")));
            imagesMap1.put(44, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/44.png")));
            imagesMap1.put(45, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/45.png")));
            imagesMap1.put(46, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/46.png")));
            imagesMap1.put(47, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/47.png")));
            imagesMap1.put(48, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/48.png")));
            imagesMap1.put(49, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/49.png")));
            imagesMap1.put(50, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/50.png")));
            imagesMap1.put(51, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/51.png")));
            imagesMap1.put(52, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/52.png")));
            imagesMap1.put(53, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/53.png")));
            imagesMap1.put(54, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/54.png")));
            imagesMap1.put(55, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/55.png")));
            imagesMap1.put(56, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/56.png")));
            imagesMap1.put(57, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/57.png")));
            imagesMap1.put(58, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/58.png")));
            imagesMap1.put(59, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/59.png")));
            imagesMap1.put(60, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/60.png")));
            imagesMap1.put(61, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/61.png")));
            imagesMap1.put(62, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/62.png")));
            imagesMap1.put(63, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/63.png")));
            imagesMap1.put(64, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/64.png")));
            imagesMap1.put(65, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/65.png")));
            imagesMap1.put(66, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/66.png")));
            imagesMap1.put(67, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/67.png")));
            imagesMap1.put(68, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/68.png")));
            imagesMap1.put(69, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/69.png")));
            imagesMap1.put(70, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/70.png")));
            imagesMap1.put(71, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/71.png")));
            imagesMap1.put(73, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/73.png")));
            imagesMap1.put(75, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/75.png")));
            imagesMap1.put(76, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/76.png")));
            imagesMap1.put(78, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/78.png")));
            imagesMap1.put(80, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/80.png")));
            imagesMap1.put(181, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/181.png")));
            imagesMap1.put(182, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/182.png")));
            imagesMap1.put(183, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/183.png")));
            imagesMap1.put(184, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/184.png")));
            imagesMap1.put(185, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/185.png")));
            imagesMap1.put(186, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/186.png")));
            imagesMap1.put(187, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/187.png")));
            imagesMap1.put(188, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/188.png")));
            imagesMap1.put(189, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/189.png")));
            imagesMap1.put(191, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/191.png")));
            imagesMap1.put(192, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/192.png")));
            imagesMap1.put(193, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/193.png")));
            imagesMap1.put(194, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/194.png")));
            imagesMap1.put(195, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/195.png")));
            imagesMap1.put(196, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/196.png")));
            imagesMap1.put(197, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/197.png")));
            imagesMap1.put(198, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/198.png")));
            imagesMap1.put(199, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/199.png")));
//            imagesMap1.put(201, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/201.png")));
            imagesMap1.put(202, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/202.png")));
            imagesMap1.put(203, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/203.png")));
            // 重复图片
            imagesMap1.put(115, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/15.png")));
            imagesMap1.put(119, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/0.png")));
            imagesMap1.put(129, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/0.png")));
            imagesMap1.put(301, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/13.png")));
            imagesMap1.put(302, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/13.png")));
            imagesMap1.put(303, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/14.png")));
            imagesMap1.put(304, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/14.png")));
            imagesMap1.put(305, ImageIO.read(new File(System.getProperty("user.dir") + "/res/map1/5.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
