package gui.panel;

import javax.swing.*;
/**
 * 抽象界面类 WorkingPanel 强制子类实现两个方法
 * WorkingPanel 存在 在 CenterPanel workingPanel 中
 * workingPanel有方法 .show(WorkingPanel p) 可以居中显示子面板 并 更新数据 即调用 this.updateData() 方法
 */
public abstract class WorkingPanel extends JPanel{
    public abstract void updateData();
    public abstract void addListener();
}
