package com.company;

import javax.swing.*;
import java.awt.*;

public abstract class Animation {
    public abstract void AnimationUp(Graphics2D g, JPanel panel);
    public abstract void AnimationDown(Graphics2D g, JPanel panel);
    public abstract void AnimationLeft(Graphics2D g, JPanel panel);
    public abstract void AnimationRight(Graphics2D g, JPanel panel);
    public abstract void AnimationDie(Graphics2D g, JPanel panel);
}
