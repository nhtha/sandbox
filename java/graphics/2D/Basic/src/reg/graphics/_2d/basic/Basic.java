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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Basic extends JFrame
{
    public TExamples type;
    enum TExamples{
        Pie3D
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        switch(type)
        {
            case Pie3D:{
                _examplePie3D(g);
                return;
            }
        }
    }
    
    public Basic()
    {
        _initComponents();
    }    
    
    private void _initComponents()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null); //to center screen
    }
    
    private float _rand(int min, int max)
    {
        return (float)(min + (Math.random() * ((max - min) + 1)));
    }    
    
    private void _examplePie3D(Graphics g)
    {
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.setPaint(new Color(184, 198, 183));
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        
        ArrayList<PieValues> values = new ArrayList<>();
        for(int i = 0; i < 20; ++i){
            values.add(new PieValues(_rand(1, 50), "text_" + i));
        }
        PieShape pie = new PieShape(74, 50, 100, 12);
        Charts.pie3DpartEnhance(g2d, pie, values.toArray(new PieValues[values.size()]), new PieParts(30, 4, -90), 15);
        Charts.linesSmoothing(g2d, 390, 50, 390, 250, new Color(24, 76, 123), 6);
        Charts.legendPrint(g2d, values.toArray(new PieValues[values.size()]), 400, 50, 15);
        Charts.linesSmoothing(g2d, 390, 250, 140, 400, new Color(24, 76, 123), 6);
    }
    
    public static void main(String[] args)
    {
        Basic frm = new Basic();
        frm.setVisible(true);
        
        frm.type = TExamples.Pie3D;
        frm.repaint();
    }
}
