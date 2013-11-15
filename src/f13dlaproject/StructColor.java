/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.util.Random;
import static f13dlaproject.Point3.*;

/**
 *
 * @author stu781945
 */
public class StructColor implements ColoringStrategy{
    
    private Color[] colors = {Color.YELLOW,Color.CYAN,Color.pink,};
    private Random r = new Random();
    
    public StructColor(Color[] c){    }
    
    @Override
    public void chooseColor(CParticle c) {
        CParticle parent = c.getParent();
        if(parent != null){
            if(parent.getParent() != null){
                Point p = c.getPos();
                Point pp = parent.getPos();
                Point dp = point3(p.getX()-pp.getX(),p.getY()-pp.getY(),p.getZ()-pp.getZ());
                if(Math.acos(pp.angleDiff(dp)) < Math.PI/3){
                    c.setColor(parent.getColor());
                }
             else{
                Color set = parent.getColor().darker();
                int re = set.getRed(), g = set.getGreen(), b = set.getBlue();
                int ave = (re+b+g)/3;
                if(ave < 20){
                    set = colors[r.nextInt(colors.length)];
                }
                c.setColor(set);
                }           
            }
            else{
                c.setColor(colors[r.nextInt(colors.length)]);
            }
        }
        else{
            c.setColor(Color.BLACK);
        }
    }
    
}
