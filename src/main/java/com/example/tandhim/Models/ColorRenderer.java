/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.tandhim.Models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;

public class ColorRenderer extends  DefaultTableCellRenderer
    {
     private int col;
     int i=0;
     public ColorRenderer()
         {
             setOpaque(true);
     }
     public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column)
         {   
             Component cell=super.getTableCellRendererComponent(table, value, false, false, row, column);
          //Component c=super.getTableCellRendererComponent();
          Object columnValue=table.getValueAt(row,5);
          Object columnValue0;
          if(row!=0)
            columnValue0=table.getValueAt(row-1,5);
          else columnValue0=null;
          


         Color [] c = {Color.white,Color.CYAN,Color.LIGHT_GRAY,Color.YELLOW};
         
         //Object columnValue=table.getValueAt(row,col);
         if (value != null) setText(value.toString());
         if(isSelected==false && hasFocus==false){
             if(columnValue.equals(columnValue0)){
                 
             
                 cell.setBackground(c[i]);
             cell.setForeground(Color.BLACK);
             }else{
                 i = (i+1)%4;
                 
                 cell.setBackground(c[i]);
             cell.setForeground(Color.BLACK);
             }
         }
         return cell;
     }
}
