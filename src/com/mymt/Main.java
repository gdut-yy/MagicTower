package com.mymt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import static com.mymt.MTGame.*;
import static java.awt.event.KeyEvent.*;
import static java.awt.event.KeyEvent.VK_J;
import static java.awt.event.KeyEvent.VK_L;


/**
 * Main 类
 * <p>
 * 运行类。
 * <p>
 * 主要是根据 魔塔 v1.12 这个版本 进行仿制。
 * <p>
 * 目前所使用的资源文件（72 pix）仅对屏幕分辨率 1920 * 1080 或以上的进行了适配。
 * 游戏主窗口大小 1296 * 936
 * 后续将适配 1280 * 720（54 pix）
 * <p>
 * GitHub 地址：
 *
 * @author ZYY
 * @since 2018-7-9
 */
public class Main {

    public static void main(String[] args) {
        try {
            game = new MTGame();
            game.setPreferredSize(new Dimension(GAME_PIX * 18, GAME_PIX * 13));

            game.add(forecastFrame);
            game.add(jumpFrame);
            game.add(battleFrame);
            game.add(msgPane);
            game.add(time);
            fr.addKeyListener(new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if (!MTGame.inConversation)
                        switch (e.getKeyCode()) {

                            case VK_DOWN:   // 键盘 ↓
                                if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0) {
//                                            playerBean_1.drct = 1;
                                    playerBean_1.setToward(1);
                                    interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                                    MTGame.fr.repaint();
                                }
                                break;
                            case VK_RIGHT:  // 键盘 →
                                if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0) {
//                                            playerBean_1.drct = 2;
                                    playerBean_1.setToward(2);
                                    interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                                    MTGame.fr.repaint();
                                }
                                break;
                            case VK_UP:     // 键盘 ↑
                                if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0) {
//                                            playerBean_1.drct = 3;
                                    playerBean_1.setToward(3);
                                    interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                                    MTGame.fr.repaint();
                                }
                                break;
                            case VK_LEFT:   // 键盘 ←
                                if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0) {
//                                            playerBean_1.drct = 0;
                                    playerBean_1.setToward(0);
                                    interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                                    fr.repaint();
                                }
                                break;
                            case VK_J:      // 键盘 J
                                if (fly) {
                                    displayJump();
                                }
                                break;
                            case VK_L:      // 键盘 L
                                if (forecast) {
                                    displayForecast();
                                }
                                break;
                        }
                    else if (e.getKeyCode() == e.VK_L)//bug
                    {
                        inConversation = false;
                        forecastFrame.removeAll();
                        forecastFrame.setVisible(false);
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            fr.setContentPane(game);
            fr.setResizable(false);
            fr.pack();
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
            fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
