/* 
  * Boost Software License - Version 1.0 - August 17th, 2003
  * 
  * Copyright (c) 2013 Developed by reg <entry.reg@gmail.com>
  * 
  * Permission is hereby granted, free of charge, to any person or organization
  * obtaining a copy of the software and accompanying documentation covered by
  * this license (the "Software") to use, reproduce, display, distribute,
  * execute, and transmit the Software, and to prepare derivative works of the
  * Software, and to permit third-parties to whom the Software is furnished to
  * do so, all subject to the following:
  * 
  * The copyright notices in the Software and this entire statement, including
  * the above license grant, this restriction and the following disclaimer,
  * must be included in all copies of the Software, in whole or in part, and
  * all derivative works of the Software, unless such copies or derivative
  * works are solely in the form of machine-executable object code generated by
  * a source language processor.
  * 
  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  * FITNESS FOR A PARTICULAR PURPOSE, TITLE AND NON-INFRINGEMENT. IN NO EVENT
  * SHALL THE COPYRIGHT HOLDERS OR ANYONE DISTRIBUTING THE SOFTWARE BE LIABLE
  * FOR ANY DAMAGES OR OTHER LIABILITY, WHETHER IN CONTRACT, TORT OR OTHERWISE,
  * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
  * DEALINGS IN THE SOFTWARE. 
*/
package reg.graphics._2d.basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * Params of Pie
 */
class PieShape
{
    public int x;
    public int y;
    public int width;
    public int height;
    public int zHeight;
    public Color border = new Color(0, 0, 0, 100);
    
    public PieShape(int x, int y, int width, int height, int zHeight)
    {
        this.x       = x;
        this.y       = y;
        this.width   = width;
        this.height  = height;
        this.zHeight = zHeight;
    }
    
    public PieShape(int x, int y, int size, int thickness)
    {
        this.x       = x;
        this.y       = y;
        this.width   = size + 50;
        this.height  = size;
        this.zHeight = thickness;
    }
}

/**
 * Wrapper primitives
 */
public class Rendering
{
    public static void pie3D(Graphics2D g, PieShape pie, int initAngle, int angle, Color c)
    {
        int cR = (int)Math.ceil(((float)c.getRed() * 0.6617));
        int cG = (int)Math.ceil(((float)c.getGreen() * 0.6671));
        int cB = (int)Math.ceil(((float)c.getBlue() * 0.6590));
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        g.setColor(new Color(cR, cG, cB, c.getAlpha()));
        for(int z = 0; z < pie.zHeight; ++z){
            g.drawArc(pie.x, pie.y + z, pie.width, pie.height, initAngle, angle);
        }
        g.fillArc(pie.x, pie.y + pie.zHeight - 1, pie.width, pie.height, initAngle, angle); //bottom
        g.setColor(new Color(cR, cG, cB, Math.min(255, c.getAlpha() + 120)));
        g.fillArc(pie.x, pie.y, pie.width, pie.height, initAngle, angle); //top
        
        //border
        g.setColor(pie.border); 
        g.drawArc(pie.x, pie.y, pie.width, pie.height, initAngle, angle);
//        g.drawArc(x, y + zHeight - 1, width, height, initAngle, angle); //TODO: 
    }
    
    public static void pie3D(Graphics2D g, PieShape pie, int angle)
    {
        pie3D(g, pie, 45, angle, new Color(98, 82, 131, 150));
    }
    
    public static void text(Graphics2D g, int x, int y, String text, int size, Color c)
    {
        //Courier New
        text(g, x, y, text, new Font("Arial", Font.PLAIN, size), c);
    }
    
    public static void text(Graphics2D g, int x, int y, String text, Font font, Color c)
    {
        AttributedString attr = new AttributedString(text);
        attr.addAttribute(TextAttribute.FONT, font);
        attr.addAttribute(TextAttribute.FOREGROUND, c);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(attr.getIterator(), x, y);
    }
    
    public static void text(Graphics2D g, int x, int y, String text)
    {
        text(g, x, y, text, new Font("Courier New", Font.PLAIN, 11), new Color(75, 147, 191, 138));
    }
    
    public static void linesSmoothing(Graphics2D g, int x, int y, int x2, int y2, Color c, int smooth)
    {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);     
        
        //main
        g.setColor(c);
        g.drawLine(x, y, x2, y2);
        
        if(smooth < 1){
            return;
        }
        
        //smoothing...
        int alpha = 90;
        int step  = (int)Math.floor((double) (alpha / smooth));
        for(int i = 0; i < smooth; ++i){
            g.setColor(new Color(c.getRed(), c.getGreen(), c.getRed(), Math.max(0, alpha - ((i + 1) * step))));
            g.drawLine(++x, y, ++x2, y2);
        }
    }
    
    public static void line(Graphics2D g, int x, int y, int x2, int y2)
    {
        linesSmoothing(g, x, y, x2, y2, new Color(0,0,0), 0);
    }
    
    public static void rectangleSmoothing(Graphics2D g)
    {
        //TODO:
    }
    
    public static void rectangle3D(Graphics2D g, int x, int y, int width, int height, boolean raised)
    {
        g.fill3DRect(x, y, width, height, raised);
    }
    
    protected Rendering(){}
}