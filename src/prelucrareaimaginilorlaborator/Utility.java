/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prelucrareaimaginilorlaborator;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 *
 * @author Student
 */
public class Utility {

    public static double Coreacteaza(double valoare){
        if(valoare>255){
            return 255;
        }else if(valoare<0){
            return 0;
        }
        return valoare;
    }


      

    public static  int adjustColor(int rgb,int dif){

        int result;
        if(dif>0){
            if(rgb+dif>255){
            result=255;
        }else{
            result=rgb+dif;
        }
       }else{
            if(rgb+dif>255){
                result=rgb+dif;
            }else{
                result=0;
            }
        }
        return result;
    }

      
    
    
    public static BufferedImage median(BufferedImage src,int M) { 

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int w,h; 

        int k,aux; 

        int m,n; 

        int med; 

        int[] sir=new int[9]; 

        w=src.getWidth(); 

        h=src.getHeight(); 

         

        for(i=1;i<w-1;i++) { 

            for(j=1;j<h-1;j++){ 

                //formarea unui sir din elementele vecinatatii 3x3 

                k=0; 

                for(m=-1;m<2;m++) { 

                    for(n=-1;n<2;n++){ 

                        int pixel = src.getRGB(i+m,j+n); 

                        Color c = new Color(pixel, true); 

                        sir[k]=c.getRed(); 

                        k++; 

                    } 

                } 

                //ordonarea crescatoare a valorilor pixelilor 

                //metoda BUBBLESORT 

                k=0; 

                while(k==0){ 

                    k=1; 

                    for(m=0;m<8;m++){ 

                        if(sir[m]>sir[m+1]){ 

                            aux=sir[m]; 

                            sir[m]=sir[m+1]; 

                            sir[m+1]=aux; 

                            k=0; 

                        } 

                    } 

                } 

                //elementul median  

                med=sir[M]; 

                Color nc = new Color(med,med,med); 

                dst.setRGB(i, j, nc.getRGB());             

            } 

        } 

        return dst; 

    } 
}
