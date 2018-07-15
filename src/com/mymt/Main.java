package com.mymt;

import com.mymt.bean.ItemsBean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.mymt.MTGame.*;
import static com.mymt.util.BattleUtil.battleLPane;
import static com.mymt.util.ForecastUtil.displayForecast;
import static com.mymt.util.ForecastUtil.forecastLPane;
import static com.mymt.util.JumpUtil.displayJump;
import static com.mymt.util.JumpUtil.jumpLPane;
import static com.mymt.util.MsgUtil.msgLPane;
import static java.awt.event.KeyEvent.*;


/**
 * Main 类
 * <p>
 * 运行类。
 *
 * @author ZYY
 * @since 2018-7-9
 */
public class Main {

    public static void main(String[] args) {
        gamePanel = new MTGame();
        gamePanel.setPreferredSize(new Dimension(GAME_PIX_72 * 18, GAME_PIX_72 * 13));

        gamePanel.add(forecastLPane);
        gamePanel.add(jumpLPane);
        gamePanel.add(battleLPane);
        gamePanel.add(msgLPane);
        gamePanel.add(timeLabel);
        gameFrame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (!MTGame.inConversation)
                    switch (e.getKeyCode()) {

                        case VK_DOWN:   // 键盘 ↓
                            if (playerBean_1.getPosY() + 1 < 11 && playerBean_1.getPosY() + 1 >= 0) {
                                playerBean_1.setToward(1);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() + 1);
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        case VK_RIGHT:  // 键盘 →
                            if (playerBean_1.getPosX() + 1 < 11 && playerBean_1.getPosX() + 1 >= 0) {
                                playerBean_1.setToward(2);
                                interaction(playerBean_1.getPosX() + 1, playerBean_1.getPosY());
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        case VK_UP:     // 键盘 ↑
                            if (playerBean_1.getPosY() - 1 < 11 && playerBean_1.getPosY() - 1 >= 0) {
                                playerBean_1.setToward(3);
                                interaction(playerBean_1.getPosX(), playerBean_1.getPosY() - 1);
                                MTGame.gameFrame.repaint();
                            }
                            break;
                        case VK_LEFT:   // 键盘 ←
                            if (playerBean_1.getPosX() - 1 < 11 && playerBean_1.getPosX() - 1 >= 0) {
                                playerBean_1.setToward(0);
                                interaction(playerBean_1.getPosX() - 1, playerBean_1.getPosY());
                                gameFrame.repaint();
                            }
                            break;
                        case VK_J:      // 键盘 J
                            if (ItemsBean.isHasJump) {
                                displayJump();
                            }
                            break;
                        case VK_L:      // 键盘 L
                            if (ItemsBean.isHasForecast) {
                                displayForecast();
                            }
                            break;
                    }
                else if (e.getKeyCode() == e.VK_L)//bug
                {
                    inConversation = false;
                    forecastLPane.removeAll();
                    forecastLPane.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        gameFrame.setContentPane(gamePanel);
        gameFrame.setResizable(false);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
