/** \file lcddraw.c
 *  \brief Adapted from RobG's EduKit
 */
#include "lcdutils.h"
#include "lcddraw.h"


/** Draw single pixel at x,row 
 *
 *  \param col Column to draw to
 *  \param row Row to draw to
 *  \param colorBGR Color of pixel in BGR
 */
void drawPixel(u_char col, u_char row, u_int colorBGR) 
{
  lcd_setArea(col, row, col, row);
  lcd_writeColor(colorBGR);
}

void drawTriangle()
{
  //(0,0), (0,15), (15,15)
  for(int i=0; i<15; i++){
    drawPixel(i, i, COLOR_ORANGE);
    drawPixel(0, i, colorBGR); //y
    drawPixel(i, 15, colorBGR); //x
  }
  //(0,0), (0,40), (20, 40)
  for(int i=0; i<40; i++){
    if(i<20){
      drawPixel(i, i*2, colorBGR); //
      drawPixel(i, (i*2)+1, colorBGR);
    }
    drawPixel(0, i, colorBGR); //y
    drawPixel(i/2, 40, colorBGR); //x
  }
}
