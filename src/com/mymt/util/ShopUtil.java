package com.mymt.util;

import com.mymt.MTGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static com.mymt.MTGame.*;
import static com.mymt.util.DialogUtil.*;

/**
 * ShopUtil 工具类
 * <p>
 * 对应各层的 商店、神秘老人与商人 类型的商店
 *
 * @author ZYY
 * @since 2018-7-14
 */
public class ShopUtil {

    private static String[] choice = new String[4];

    // 商店事件
    public static void shop(int id) {
        switch (id) {
            case 0:     // 第 3 层商店
                choice = new String[]{"▶增加 800 点生命（25 金币）", "▷增加 4 点攻击（25 金币）", "▷增加 4 点防御（25 金币）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(22)));
                break;
            case 1:     // 第 5 层 神秘老人
                choice = new String[]{"▶提升一级（需要 100 点）", "▷增加攻击5（需要 30 点） ", "▷增加防御5（需要 30 点）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(26)));
                break;
            case 2:     // 第 5 层 商人
                choice = new String[]{"▶购买 1 把黄钥匙（$ 10）", "▷购买 1 把蓝钥匙（$ 50）", "▷购买 1 把红钥匙（$ 100）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(27)));
                break;
            case 3:     // 第 11 层 商店
                choice = new String[]{"▶增加 4000 点生命（100 金币）", "▷增加 20 点攻击（100 金币）", "▷增加 20 点防御（100 金币）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(22)));
                break;
            case 4:     // 第 12 层 商人
                choice = new String[]{"▶卖出 1 把黄钥匙（$ 7）", "▷卖出 1 把黄钥匙（$ 35）", "▷卖出 1 把黄钥匙（$ 70）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(27)));
                break;
            case 5:     // 第 13 层 神秘老人
                choice = new String[]{"▶提升三级（需要 270 点）", "▷增加攻击 17（需要 95 点）", "▷增加防御 17（需要 95 点）", "▷离开商店"};
                imgIco.setIcon(new ImageIcon(MTGame.imgSource.get(26)));
                break;
        }

        Insets insets = dialogLPane.getInsets();
        imgIco.setBounds(20 + insets.left, 20 + insets.top, GAME_PIX_72, GAME_PIX_72);
        text.setBounds(100 + insets.left, 20 + insets.top, 550 - 50, 250);
        text.setText("请选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3]);
        dialogBgImg.setBounds(0, 0, 550, 250);
        dialogLPane.setBounds(550, 230, 550, 250);
        dialogLPane.add(imgIco, 2, 0);
        dialogLPane.add(text, 3, 0);
        gamePanel.add(dialogLPane);
        gamePanel.repaint();

        gameFrame.addKeyListener(new KeyListener() {
            int selection = 0;
            String message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (selection != 3 && e.getKeyCode() == e.VK_S) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection + 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                    text.setText(message);
                    gameFrame.repaint();
                }
                if (selection != 0 && e.getKeyCode() == e.VK_W) {
                    choice[selection] = choice[selection].replaceAll("▶", "▷");
                    selection = selection - 1;
                    choice[selection] = choice[selection].replaceAll("▷", "▶");
                    message = "选择一个: \n " + choice[0] + " \n " + choice[1] + " \n " + choice[2] + " \n " + choice[3];
                    text.setText(message);
                    gameFrame.repaint();
                }
                if (e.getKeyCode() == e.VK_SPACE) {
                    switch (id) {
                        case 0:     // 对应 3 楼的商店选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setHp(playerBean_1.getHp() + 800);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 4);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 25) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 25);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 4);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 1:     // 对应 5 楼的老人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getExp() >= 100) {
                                        playerBean_1.setLevel(playerBean_1.getLevel() + 1);
                                        playerBean_1.setExp(playerBean_1.getExp() - 100);
                                        playerBean_1.setHp(playerBean_1.getHp() + 1000);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 7);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 7);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getExp() >= 30) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 30);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 5);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getExp() >= 30) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 30);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 5);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 2:     // 对应 5 楼的商人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 10) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 10);
                                        playerBean_1.setYkey(playerBean_1.getYkey() + 1);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 50) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 50);
                                        playerBean_1.setBkey(playerBean_1.getBkey() + 1);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setRkey(playerBean_1.getRkey() + 1);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 3:     // 对应 11 楼的商店选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setHp(playerBean_1.getHp() + 4000);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 20);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getMoney() >= 100) {
                                        playerBean_1.setMoney(playerBean_1.getMoney() - 100);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 20);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 4:     // 对应  楼的商人选项
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getYkey() > 0) {
                                        playerBean_1.setYkey(playerBean_1.getYkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 7);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getBkey() > 0) {
                                        playerBean_1.setBkey(playerBean_1.getBkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 35);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getRkey() > 0) {
                                        playerBean_1.setRkey(playerBean_1.getRkey() - 1);
                                        playerBean_1.setMoney(playerBean_1.getMoney() + 70);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                        case 5:
                            switch (selection) {
                                case 0:
                                    if (playerBean_1.getExp() >= 270) {
                                        playerBean_1.setLevel(playerBean_1.getLevel() + 3);
                                        playerBean_1.setExp(playerBean_1.getExp() - 270);
                                        playerBean_1.setHp(playerBean_1.getHp() + 3000);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 21);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 21);
                                    }
                                    break;
                                case 1:
                                    if (playerBean_1.getExp() >= 95) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 95);
                                        playerBean_1.setAttack(playerBean_1.getAttack() + 17);
                                    }
                                    break;
                                case 2:
                                    if (playerBean_1.getExp() >= 95) {
                                        playerBean_1.setExp(playerBean_1.getExp() - 95);
                                        playerBean_1.setDefend(playerBean_1.getDefend() + 17);
                                    }
                                    break;
                                case 3:
                                    dialogLPane.remove(imgIco);
                                    dialogLPane.remove(text);
                                    gamePanel.remove(dialogLPane);
                                    gameFrame.repaint();
                                    inConversation = false;
                                    gameFrame.removeKeyListener(this);
                                    break;
                            }
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}
