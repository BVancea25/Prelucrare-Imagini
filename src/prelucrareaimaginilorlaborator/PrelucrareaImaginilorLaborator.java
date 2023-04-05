package prelucrareaimaginilorlaborator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package prelucrareaimaginilorlaborator;

import java.awt.Color;
import java.awt.Paint;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.Math;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javafx.scene.control.Alert;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;


/**
 *
 * @bogdan
 *
 *
API-urile JavaFX au fost portate direct în Java, bazându-se mai mult pe standarde Internet, cum ar fi CSS pentru stilizarea controalelor sau ARIA pentru specificări referitoare la accesibilitate.
 
Graful de Scene
Implementarea unei aplicații JavaFX implică proiectarea și dezvoltarea unui graf de scene (eng. Scene Graph), 
structură ierarhică de noduri ce conţine elementele vizuale ale interfeţei grafice cu utilizatorul, 
care poate trata diferite evenimente legate de acestea şi care poate fi redată.

Un element din graful de scene (= un nod) este identificat în mod unic, fiind caracterizat printr-o clasă de stil şi 
un volum care îl delimitează. 
Fiecare nod are un părinte (cu excepția nodului rădăcină), putând avea nici unul, unul sau mai mulţi copii.
De asemenea, pentru un astfel de element pot fi definite efecte (estompări sau umbre), opacitate, transformări, 
mecanisme de tratare a diferitelor evenimente (care vizează interacţiunea cu utilizatorul) precum şi starea aplicaţiei.

Spre diferenţă de Swing sau AWT (Abstract Window Toolkit), JavaFX conţine pe lângă mecanisme de dispunere a conţinutului, controale, imagini sau obiecte multimedia şi 
primitive pentru elemente grafice (ca fi texte sau figuri geometice cu care se pot crea animaţii, folosind metodele puse la dispoziţie de API-urile javafx.animation).

API-ul javafx.scene permite construirea următoarelor conţinuturi:

noduri: forme 2D şi 3D, imagini, conţinut multimedia şi conţinut Internet, text, controale pentru interacţiunea cu utilizatorul, grafice, containere;
stări: transformări (poziţionări şi orientări ale nodurilor), efecte vizuale;
efecte: obiecte care modifică aspectul nodurilor (mecanisme de estompare, umbre, reglarea culorilor).
Mecanisme de Dispunere a Conţinutului
Controalele din graful de scene pot fi grupate în containere sau panouri în mod flexibil, folosind mai multe mecanisme de dispunere a conținutului (eng. layout).

API-ul JavaFX defineşte mai multe clase de tip container pentru dispunerea elementelor, în pachetul javafx.scene.layout:

BorderPane dispune nodurile conţinute în regiunile de sus, jos, dreapta, stânga sau centru;
HBox îşi aranjează conţinutul orizontal pe un singur rând;
VBox îşi aranjează conţinutul vertical pe o singură coloană;
StackPane utilizează o stivă de noduri afişând elementele unele peste altele, din spate către față;
GridPane permite utilizatorului să îşi definească un tabel (format din rânduri şi coloane) în care să poată fi încadrate elementele conţinute;
FlowPane dispune elementele fie orizontal, fie vertical, în funcţie de limitele specificate de programator (lungime pentru dispunere orizontală, respectiv înălţime pentru dispunere verticală);
TilePane plasează nodurile conţinute în celule de dimensiuni uniforme;
AnchorPane oferă programatorilor posibilitatea de a defini noduri ancoră (referinţă) în funcţie de colţurile de jos / sus, din stânga / dreapta sau raportat la centrul containerului sau panoului.
Diferitele moduri de dispunere pot fi imbricate în cadrul unei aplicaţii JavaFX pentru a se obţine funcţionalitatea dorită.
 
 */
public class PrelucrareaImaginilorLaborator extends Application {
    BufferedImage bufferedImag;
    Label name;
    
    @Override
    public void start(Stage mainStage) {

        //Avem nevoie de o modalitate de a alege un fisier imagine
        //Vom folosi FileChooser
        //
        ImageView imageView = new ImageView();

        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("Fisiere");
        MenuItem menuItem_Open = new MenuItem("Afiseaza Imagine");
        MenuItem menuItem_Save = new MenuItem("Salveaza Imagine");

        MenuItem menuItemGrayScale=new MenuItem("GrayScale");

        MenuItem menuItemYUV=new MenuItem("YUV");

        MenuItem menuItemBW=new MenuItem("Black and White");

        MenuItem menuItemInvert=new MenuItem("Invert");

        MenuItem menuItemOriginal=new MenuItem("Original");

        MenuItem menuItemHSV=new MenuItem("HSV");
        
        MenuItem menuItem_Grayscale2=new MenuItem("GrayScale2");
        
        MenuItem menuItem_Grayscale3=new MenuItem("GrayScale3");
        
        MenuItem menuItem_Grayscale4=new MenuItem("GrayScale4");
        
        MenuItem menuItem_Histograma=new MenuItem("Histograma");


        Menu menuFiltre=new Menu("Filtre");

        MenuItem menuItem_Accentuare=new Menu("Accentuare");
        
         MenuItem menuItem_Mediere=new MenuItem("Mediere");
         
         MenuItem menuItem_FiltruMedian=new MenuItem("Filtru Median");

         

       

        SeparatorMenuItem sep = new SeparatorMenuItem();
        MenuItem menuItem_Exit = new MenuItem("Iesire");
        menuFile.getItems().addAll(menuItem_Open,menuItem_Save,sep,menuItem_Exit);

        Menu menuChange = new Menu("Modificare");

      

        MenuItem menuItem_RGB = new MenuItem("RGB");

        menuItem_RGB.setDisable(true);
        menuItemGrayScale.setDisable(true);
        menuItemYUV.setDisable(true);
        menuItemOriginal.setDisable(true);
        menuItemInvert.setDisable(true);
        menuItemBW.setDisable(true);
        menuItemHSV.setDisable(true);
        menuItem_Grayscale3.setDisable(true);
        menuItem_Grayscale2.setDisable(true);
        menuItem_Grayscale4.setDisable(true);
        menuItem_Histograma.setDisable(true);
        menuItem_Mediere.setDisable(true);
        menuItem_FiltruMedian.setDisable(true);
        menuItem_Accentuare.setDisable(true);
        

        menuFiltre.getItems().addAll(menuItem_Mediere,menuItem_FiltruMedian,menuItem_Accentuare);

        menuChange.getItems().addAll(menuItem_RGB,menuItemGrayScale,menuItemYUV,menuItemOriginal,menuItemBW,menuItemHSV,menuItem_Grayscale2,menuItem_Grayscale3,menuItem_Grayscale4,menuItem_Histograma);

        menuBar.getMenus().addAll(menuFile, menuChange,menuFiltre);

        VBox vbox = new VBox(menuBar);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(0, 10, 0, 10));

        ScrollPane sp = new ScrollPane();
        vbox.getChildren().add(sp);
        
        menuItem_Open.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Afiseaza Imagine");

         
            
            File file = fileChooser.showOpenDialog(mainStage);
            if (file != null) {
                try {
                    VBox vbOpen = new VBox();
 
                    name = new Label(file.getAbsolutePath());
                    bufferedImag = ImageIO.read(file);

                    BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
                    
                    for (int y = 0; y < bufferedImag.getHeight(); y++) {
                        for (int x = 0; x < bufferedImag.getWidth(); x++) {
                           //Retrieving contents of a pixel
                           int pixel = bufferedImag.getRGB(x,y);
                           //Creating a Color object from pixel value
                           Color color = new Color(pixel, true);
                           //Retrieving the R G B values
                           int red = color.getRed();
                           int green = color.getGreen();
                           int blue = color.getBlue();
                           Color myWhite = new Color(red, green, blue);
                           imageN.setRGB(x, y, myWhite.getRGB());
                        }
                    }
        
                    Image image = SwingFXUtils.toFXImage(imageN, null);
                    
                    vbOpen.getChildren().addAll(name,imageView);

                    imageView.setFitHeight(400);
                    imageView.setPreserveRatio(true);
                    imageView.setImage(image);
                    imageView.setSmooth(true);
                    imageView.setCache(true);
                    
                    sp.setContent(vbOpen);
                    
                    for(int i=0;i<menuBar.getMenus().get(1).getItems().size();i++){
                        menuBar.getMenus().get(1).getItems().get(i).setDisable(false);
                    }

                    for(int i=0;i<menuBar.getMenus().get(2).getItems().size();i++){
                        menuBar.getMenus().get(2).getItems().get(i).setDisable(false);
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        menuItem_Save.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvare Imagine");
            
            File file = fileChooser.showSaveDialog(mainStage);
            if (file != null) {
                try {
                    ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(),null), "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(PrelucrareaImaginilorLaborator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        
        menuItem_Grayscale3.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 
            
            BufferedImage imageH = this.getGrayscaleImage(bufferedImag);
            
            Image imgH = SwingFXUtils.toFXImage(imageH, null); 

            ImageView imageViewH = new ImageView(); 

            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 

            imageViewH.setImage(imgH); 

            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 

            spH.setContent(imageViewH); 

 

            VBox vbH = new VBox(); 

             

            vbH.getChildren().addAll(imageViewH); 

            spH.setContent(vbH); 



           Stage secondStage = new Stage(); 

 

            Scene sceneH = new Scene(new HBox(), 400, 350); 
           ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 



            secondStage.setTitle(name.getText()); 

           secondStage.setScene(sceneH); 

           secondStage.show();             

       }); 


       menuItem_Accentuare.setOnAction((ActionEvent event)->{

        ScrollPane spH=new ScrollPane();
        BufferedImage imageH=this.accentuare(bufferedImag);

        Image imgH = SwingFXUtils.toFXImage(imageH, null); 
        ImageView imageViewH = new ImageView(); 
        imageViewH.setFitHeight(400); 
        imageViewH.setPreserveRatio(true); 
         imageViewH.setImage(imgH); 

         imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 


            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 
     
            VBox vbH = new VBox(); 
             
            vbH.getChildren().addAll(imageViewH); 

            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

            secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

            secondStage.show();   
        });
         
       
       menuItem_FiltruMedian.setOnAction((ActionEvent event)->{
         ScrollPane spH=new ScrollPane();
         
         BufferedImage imageH=Utility.median(bufferedImag, 4);
                 
                  
            Slider sliderMedian = new Slider(0, 8, 4);//creare slider pt culoarea RED
            sliderMedian.setShowTickLabels(true);
            sliderMedian.setMajorTickUnit(1);
            sliderMedian.setBlockIncrement(1);
            Image imgH = SwingFXUtils.toFXImage(imageH, null); 
            ImageView imageViewH = new ImageView(); 

            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 

            imageViewH.setImage(imgH); 
            
           sliderMedian.valueProperty().addListener(new ChangeListener<Number>() {
        

             @Override
             public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                 
                 
                 BufferedImage aux=Utility.median(bufferedImag, newValue.intValue());
                 Image imgAUX = SwingFXUtils.toFXImage(aux, null);
                 imageViewH.setImage(imgAUX);
                 
                //To change body of generated methods, choose Tools | Templates.
             }
        });
            
            
 
            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 


            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 
     
            VBox vbH = new VBox(); 
             
            vbH.getChildren().addAll(imageViewH,sliderMedian); 

            spH.setContent(vbH); 

            Stage secondStage = new Stage(); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

 

            secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

            secondStage.show();   
       
       });
        
        menuItem_Mediere.setOnAction((ActionEvent event)->{
        
        ScrollPane spH = new ScrollPane();

         BufferedImage imageH = this.mediere(bufferedImag); 
 
            Image imgH = SwingFXUtils.toFXImage(imageH, null); 

            ImageView imageViewH = new ImageView(); 

            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 

            imageViewH.setImage(imgH); 

            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 

            spH.setContent(imageViewH); 

 

            VBox vbH = new VBox(); 

             

            vbH.getChildren().addAll(imageViewH); 

            spH.setContent(vbH); 

 

            Stage secondStage = new Stage(); 

 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

 

            secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

            secondStage.show();             
        
        
        });
            
            

        menuItem_RGB.setOnAction((ActionEvent event) -> {
            ScrollPane spRGB = new ScrollPane();

            BufferedImage imageN = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            

           

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int green = color.getGreen();
                   int blue = color.getBlue();
                   Color myWhite = new Color(red, green, blue);
                   imageN.setRGB(x, y, myWhite.getRGB());
                }
            }
            

            Image image = SwingFXUtils.toFXImage(imageN, null);
            ImageView imageViewRGB = new ImageView();
            imageViewRGB.setFitHeight(400);
            imageViewRGB.setPreserveRatio(true);
            imageViewRGB.setImage(image);
            imageViewRGB.setSmooth(true);
            imageViewRGB.setCache(true);         

            Slider sliderR = new Slider(0, 255, 0);//creare slider pt culoarea RED
            sliderR.setShowTickLabels(true);
            sliderR.setMajorTickUnit(10);
            sliderR.setBlockIncrement(1);

            Slider sliderG = new Slider(0, 255, 0);//creare slider pt culoarea GREEN
            sliderG.setShowTickLabels(true);
            sliderG.setMajorTickUnit(10);
            sliderG.setBlockIncrement(1);

            sliderR.setOnMouseReleased(eventDragR->{
              
              for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                       //Retrieving contents of a pixel
                       int pixel = bufferedImag.getRGB(x,y);
                       //Creating a Color object from pixel value
                       Color color = new Color(pixel, true);
                       //Retrieving the R G B values
                       int red = color.getRed();
                       int green = color.getGreen();
                       int blue = color.getBlue();
                       int newRed=red+(int)sliderR.getValue();
                       if(newRed>255){
                         newRed=255;
                       }
                       Color myWhite = new Color(newRed, green, blue);
                       imageN.setRGB(x, y, myWhite.getRGB());
                    }

                  }
              Image newImage = SwingFXUtils.toFXImage(imageN, null);
              imageViewRGB.setImage(newImage);
            });

            sliderG.setOnMouseReleased(eventDragG->{
              
              for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                       //Retrieving contents of a pixel
                       int pixel = bufferedImag.getRGB(x,y);
                       //Creating a Color object from pixel value
                       Color color = new Color(pixel, true);
                       //Retrieving the R G B values
                       int red = color.getRed();
                       int green = color.getGreen();
                       int blue = color.getBlue();
                       int newGreen=green+(int)sliderG.getValue();
                       if(newGreen>255){
                         newGreen=255;
                       }
                       Color myWhite = new Color(red, newGreen, blue);
                       imageN.setRGB(x, y, myWhite.getRGB());
                    }

                  }
              Image newImage = SwingFXUtils.toFXImage(imageN, null);
              imageViewRGB.setImage(newImage);
            });

            Slider sliderB = new Slider(0, 255, 0);//creare slider pt culoarea GREEN
            sliderB.setShowTickLabels(true);
            sliderB.setMajorTickUnit(10);
            sliderB.setBlockIncrement(1);

            sliderB.setOnMouseReleased(eventDragB->{
              
              for (int y = 0; y < bufferedImag.getHeight(); y++) {
                    for (int x = 0; x < bufferedImag.getWidth(); x++) {
                       //Retrieving contents of a pixel
                       int pixel = bufferedImag.getRGB(x,y);
                       //Creating a Color object from pixel value
                       Color color = new Color(pixel, true);
                       //Retrieving the R G B values
                       int red = color.getRed();
                       int green = color.getGreen();
                       int blue = color.getBlue();
                       int newBlue=blue+(int)sliderB.getValue();
                       if(newBlue>255){
                         newBlue=255;
                       }
                       Color myWhite = new Color(red, green, newBlue);
                       imageN.setRGB(x, y, myWhite.getRGB());
                    }

                  }
              Image newImage = SwingFXUtils.toFXImage(imageN, null);
              imageViewRGB.setImage(newImage);
            });

           
            
      
            
            
            Button reset=new Button("Reset");
            reset.setOnAction(new EventHandler<ActionEvent>(){
                        @Override
                        public void handle(ActionEvent event) {

                            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                            for (int x = 0; x < bufferedImag.getWidth(); x++) {
                               //Retrieving contents of a pixel
                               int pixel = bufferedImag.getRGB(x,y);
                               //Creating a Color object from pixel value
                               Color color = new Color(pixel, true);
                               //Retrieving the R G B values
                               int red = color.getRed();
                               int green = color.getGreen();
                               int blue = color.getBlue();
                             
                               Color myWhite = new Color(red,green,blue);
                               imageN.setRGB(x, y, myWhite.getRGB());
                            }

                          }
                      Image newImage = SwingFXUtils.toFXImage(imageN, null);
                      imageViewRGB.setImage(newImage);
                        }
            
            
                    });
            
                      
            
            
            
            
            Label labelR=new Label("RED");
            HBox hboxRL=new HBox();
            hboxRL.setAlignment(Pos.CENTER_LEFT);
            hboxRL.getChildren().addAll(labelR,sliderR);
            hboxRL.setSpacing(25);
            
            VBox vboxButtons=new VBox();
            vboxButtons.setAlignment(Pos.CENTER);
            vboxButtons.getChildren().addAll(reset);
            vboxButtons.setSpacing(10);
            

            Label labelG=new Label("GREEN");
            HBox hboxGL=new HBox();
            hboxGL.setAlignment(Pos.CENTER_LEFT);
            hboxGL.getChildren().addAll(labelG,sliderG);
            hboxGL.setSpacing(10);

            Label labelB=new Label("BLUE");
            HBox hboxBL=new HBox();
            hboxBL.setAlignment(Pos.CENTER_LEFT);
            hboxBL.getChildren().addAll(labelB,sliderB);
            hboxBL.setSpacing(20);


            spRGB.setContent(imageViewRGB);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB,hboxRL,hboxGL,hboxBL,vboxButtons);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
        });
        
        
        
        menuItem_Grayscale2.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 

 

            BufferedImage imageH = this.getGrayscaleImage(bufferedImag); 

 

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 

            ImageView imageViewH = new ImageView(); 

            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 

            imageViewH.setImage(imgH); 

            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 

            spH.setContent(imageViewH); 

 

           VBox vbH = new VBox(); 

             

            vbH.getChildren().addAll(imageViewH); 

            spH.setContent(vbH); 

 

            Stage secondStage = new Stage(); 

 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

 

           secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

           secondStage.show();             

        }); 
        
        menuItemInvert.setOnAction((ActionEvent event)->{
         
         ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            
           

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int blue=color.getBlue();
                   int green=color.getGreen();
                   
                   
                   int C=255-red;
                   int M =255-green;
                   int Y=255-blue;
                   
                  
                   Color CMY = new Color(C, M, Y);
                   
                       
                   image1.setRGB(x, y, CMY.getRGB());
                   
                }
            }
     
            
            Image imageInvert = SwingFXUtils.toFXImage(image1, null);
            
    
            ImageView imageViewInvert = new ImageView();
           
            imageViewInvert.setFitHeight(400);
            imageViewInvert.setPreserveRatio(true);
            imageViewInvert.setImage(imageInvert);
            imageViewInvert.setSmooth(true);
            imageViewInvert.setCache(true);
            
            
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewInvert);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
         });



       menuItemBW.setOnAction((ActionEvent event)->{
         
            ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            
          
                    
                   for (int y = 0; y < bufferedImag.getHeight(); y++) {
                       for (int x = 0; x < bufferedImag.getWidth(); x++) {
                          //Retrieving contents of a pixel
                          int pixel = bufferedImag.getRGB(x,y);
                          //Creating a Color object from pixel value
                           Color color = new Color(pixel, true);
                          //Retrieving the R G B values
                          int red = color.getRed();
                          int green = color.getGreen();
                          int blue = color.getBlue();
                          Color myWhite = new Color(red, green, blue);
                          image1.setRGB(x, y, myWhite.getRGB());
                       }
                   }

                 Slider sliderBW = new Slider(0, 255, 0);
                 sliderBW.setShowTickLabels(true);
                 sliderBW.setMajorTickUnit(10);
                 sliderBW.setBlockIncrement(1);
                 ImageView imageViewBW = new ImageView();
                  Image imageBW = SwingFXUtils.toFXImage(image1, null);

                  sliderBW.setOnMouseReleased(eventDragBW->{

                   for (int y = 0; y < bufferedImag.getHeight(); y++) {
                         for (int x = 0; x < bufferedImag.getWidth(); x++) {
                            //Retrieving contents of a pixel
                            int pixel = bufferedImag.getRGB(x,y);
                            //Creating a Color object from pixel value
                            Color color = new Color(pixel, true);
                            //Retrieving the R G B values
                            int red = color.getRed();
                            int green = color.getGreen();
                            int blue = color.getBlue();
                            int value=(int)sliderBW.getValue();
                            int grayScale=(int)((red+green+blue)/3);
                            if(grayScale>value){
                              grayScale=255;
                            }else{
                             grayScale=0;
                             }
                            Color myWhite = new Color(grayScale, grayScale, grayScale);
                            image1.setRGB(x, y, myWhite.getRGB());
                         }

                       }
                       Image imageM = SwingFXUtils.toFXImage(image1, null);
                       imageViewBW.setImage(imageM);
                  
                 });
            
           
            
    
            
           
            imageViewBW.setFitHeight(400);
            imageViewBW.setPreserveRatio(true);
            imageViewBW.setImage(imageBW);
            imageViewBW.setSmooth(true);
            imageViewBW.setCache(true);
            
            
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewBW,sliderBW);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
         });

        menuItemYUV.setOnAction((ActionEvent event)->{
         
         ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage image2 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
           

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int blue=color.getBlue();
                   int green=color.getGreen();
                   
                   
                   int Y=(int)(0.3*red+0.6*green+0.1*blue);
                   int U = (int) (0.74*(red-Y)+0.27*(blue-Y));
                   int V=(int)(0.48*(red-Y)+0.41*(blue-Y));
                   
                   int Y2=(int)(0.299*red+0.587*green+0.114*blue);
                   int Cb=(int)(-0.1687*red-0.3313*green + 0.498*blue + 128) ;
                   int Cr=(int)(  0.498*red - 0.4187*green - 0.0813*blue + 128 );
                   
                   if(Y>255){
                     Y=255;
                   }
                   if(U>255){
                     U=255;   
                   }
                   if(V>255){
                     V=255;
                   }
                   if(Y<0){
                       Y=0;
                   }
                   if(U<0){
                       U=0;
                   }
                   if(V<0){
                       V=0;
                   }
                   
                   if(Y2>255){
                     Y2=255;
                   }
                   if(Cr>255){
                     Cr=255;   
                   }
                   if(Cb>255){
                     Cb=255;
                   }
                   if(Y2<0){
                       Y2=0;
                   }
                   if(Cr<0){
                       Cr=0;
                   }
                   if(Cb<0){
                       Cb=0;
                   }
                   
                   Color YUV = new Color(Y, U, V);
                   Color YBR = new Color(Y, Cb, Cr);
                       
                   image1.setRGB(x, y, YUV.getRGB());
                   image2.setRGB(x,y,YBR.getRGB());
                   
                   
                }
            }
     
            

            Image imageYUV = SwingFXUtils.toFXImage(image1, null);
            
            Image imageYBR = SwingFXUtils.toFXImage(image2, null);
            
            
            
            ImageView imageViewYUV = new ImageView();
            ImageView imageViewYBR = new ImageView();
            
            
            imageViewYUV.setFitHeight(400);
            imageViewYUV.setPreserveRatio(true);
            imageViewYUV.setImage(imageYUV);
            imageViewYUV.setSmooth(true);
            imageViewYUV.setCache(true);
            
            imageViewYBR.setFitHeight(400);
            imageViewYBR.setPreserveRatio(true);
            imageViewYBR.setImage(imageYBR);
            imageViewYBR.setSmooth(true);
            imageViewYBR.setCache(true);
            
            
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewYUV,imageViewYBR);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
         });

        menuItemGrayScale.setOnAction((ActionEvent event) -> {
            ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage image2 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            BufferedImage image3 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int blue=color.getBlue();
                   int green=color.getGreen();
                   int gray1=(red+blue+green)/3;
                   int gray2 = (int) ((int)(0.29*red)+(0.58*green)+(0.11*blue));
                   int gray3= (int)(Math.min(Math.min(red, blue),green)/2 + (Math.max(Math.max(red,blue),green)/2)); 
                   
                   Color myGray1 = new Color(gray1, gray1, gray1);
                   Color myGray2 = new Color(gray2, gray2, gray2);
                   Color myGray3=new Color(gray3,gray3,gray3);
                   image1.setRGB(x, y, myGray1.getRGB());
                   image2.setRGB(x,y,myGray2.getRGB());
                   image3.setRGB(x,y,myGray3.getRGB());
                   
                }
            }
     
            

            Image imageG1 = SwingFXUtils.toFXImage(image1, null);
            
            Image imageG2 = SwingFXUtils.toFXImage(image2, null);
            
            Image imageG3 = SwingFXUtils.toFXImage(image3, null);
            
            ImageView imageViewG1 = new ImageView();
            ImageView imageViewG2 = new ImageView();
            ImageView imageViewG3 = new ImageView();
            
            imageViewG1.setFitHeight(400);
            imageViewG1.setPreserveRatio(true);
            imageViewG1.setImage(imageG1);
            imageViewG1.setSmooth(true);
            imageViewG1.setCache(true);
            
            imageViewG2.setFitHeight(400);
            imageViewG2.setPreserveRatio(true);
            imageViewG2.setImage(imageG2);
            imageViewG2.setSmooth(true);
            imageViewG2.setCache(true);
            
            imageViewG3.setFitHeight(400);
            imageViewG3.setPreserveRatio(true);
            imageViewG3.setImage(imageG3);
            imageViewG3.setSmooth(true);
            imageViewG3.setCache(true);
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewG1,imageViewG2,imageViewG3);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
        });
        
        menuItem_Grayscale4.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 

 

           BufferedImage imageH = this.equalize(bufferedImag); 
 

            Image imgH = SwingFXUtils.toFXImage(imageH, null); 

            ImageView imageViewH = new ImageView(); 

            imageViewH.setFitHeight(400); 

            imageViewH.setPreserveRatio(true); 

            imageViewH.setImage(imgH); 

            imageViewH.setSmooth(true); 

            imageViewH.setCache(true); 

            spH.setContent(imageViewH); 

 

            VBox vbH = new VBox(); 

             

            vbH.getChildren().addAll(imageViewH); 

            spH.setContent(vbH); 

 

            Stage secondStage = new Stage(); 

 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            ((HBox)sceneH.getRoot()).getChildren().addAll(spH); 

 

            secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

            secondStage.show();             

       }); 
        
        menuItem_Histograma.setOnAction((ActionEvent event) -> { 

            ScrollPane spH = new ScrollPane(); 

            Stage secondStage = new Stage(); 

  

            SwingNode sn = new SwingNode(); 

            ChartPanel cn = this.createChartPanel(bufferedImag); 

            sn.setContent(cn); 

  

            spH.setContent(sn); 

            Scene sceneH = new Scene(new HBox(), 400, 350); 

            HBox hb = (HBox) sceneH.getRoot(); 

            hb.getChildren().addAll(spH); 

  

            secondStage.setTitle(name.getText()); 

            secondStage.setScene(sceneH); 

            secondStage.show(); 

   }); 

        menuItemOriginal.setOnAction((ActionEvent event)->{
         
         ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_RGB);
            

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int blue=color.getBlue();
                   int green=color.getGreen();
                   
                   
                   int Y=(int)(0.299*red+0.587*green+0.114*blue);
                   int U = (int) (-0.055*red + 0.369*green + -0.314*blue);
                   int V=(int)(0.809*red + -0.409*green + -0.400);
                   
                   if(Y>255){
                     Y=255;
                   }
                   if(U>255){
                     U=255;   
                   }
                   if(V>255){
                     V=255;
                   }
                   if(Y<0){
                       Y=0;
                   }
                   if(U<0){
                       U=0;
                   }
                   if(V<0){
                       V=0;
                   }
                   
                  
                   
                   Color YUV = new Color(Y, U, V);
                 
                       
                   image1.setRGB(x, y, YUV.getRGB());
                                     
                }
            }
     
            

            Image imageYUV = SwingFXUtils.toFXImage(image1, null);
                      
            
            
            ImageView imageViewYUV = new ImageView();
            
            
            
            imageViewYUV.setFitHeight(400);
            imageViewYUV.setPreserveRatio(true);
            imageViewYUV.setImage(imageYUV);
            imageViewYUV.setSmooth(true);
            imageViewYUV.setCache(true);
            
                 
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewYUV);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
         });


         menuItemHSV.setOnAction((ActionEvent event)->{
         
         ScrollPane spRGB = new ScrollPane();

            BufferedImage image1 = new BufferedImage(bufferedImag.getWidth(), bufferedImag.getHeight(), BufferedImage.TYPE_INT_ARGB);
            

            for (int y = 0; y < bufferedImag.getHeight(); y++) {
                for (int x = 0; x < bufferedImag.getWidth(); x++) {
                   //Retrieving contents of a pixel
                   int pixel = bufferedImag.getRGB(x,y);
                   //Creating a Color object from pixel value
                   Color color = new Color(pixel, true);
                   //Retrieving the R G B values
                   int red = color.getRed();
                   int blue=color.getBlue();
                   int green=color.getGreen();
                   
                   float r=(float)red/255;
                   float b=(float)blue/255;
                   float g=(float)green/255;

                   float M=Math.max(r,Math.max(g,b));
                   float m=Math.min(r,Math.min(g,b));

                   float C=M-m;

                   float V=M;

                   float S;

                   if(V!=0){
                        S=C/V;
                    }else{S=0;}

                    float H=0;

                    if (C!=0) { 

                    if (M == r) H = 60 * (g - b) / C; 

                    if (M == g) H = 120 + 60 * (b - r) / C; 

                    if (M == b) H = 240 + 60 * (r - g) / C; 

                    }else{H=0;}
                    
                    if(H<0){H=H+360;}
                    
                    int H_n=(int)Math.round(H*255/360);
                    int S_n=(int)Math.round(S*255);
                    int V_n=(int)Math.round(V*255);

                    if(H_n<0){H_n=0;}else if(H_n>255){H_n=255;}
                    if(S_n<0){S_n=0;}else if(S_n>255){S_n=255;}
                    if(V_n<0){V_n=0;}else if(V_n>255){V_n=255;}
                    
                    Color HSV=new Color(H_n,S_n,V_n);
                    
                                     
                   image1.setRGB(x, y, HSV.getRGB());
                                     
                }
            }
     
            

            Image imageYUV = SwingFXUtils.toFXImage(image1, null);
                      
            
            
            ImageView imageViewYUV = new ImageView();
            
            
            
            imageViewYUV.setFitHeight(400);
            imageViewYUV.setPreserveRatio(true);
            imageViewYUV.setImage(imageYUV);
            imageViewYUV.setSmooth(true);
            imageViewYUV.setCache(true);
            
                 
            
            HBox rgbBox=new HBox();
            rgbBox.getChildren().addAll(imageViewYUV);
            spRGB.setContent(rgbBox);
            
            Stage secondStage = new Stage();
            VBox vboxRGB = new VBox();
            vboxRGB.setAlignment(Pos.CENTER);
            vboxRGB.setSpacing(10);
            vboxRGB.setPadding(new Insets(0, 10, 0, 10));
            vboxRGB.getChildren().addAll(spRGB);

            Scene sceneRGB = new Scene(new VBox(), 400, 350);
            ((VBox)sceneRGB.getRoot()).getChildren().addAll(vboxRGB);

            secondStage.setTitle(name.getText());
            secondStage.setScene(sceneRGB);
            secondStage.show();            
         });
        
       
        
        menuItem_Exit.setOnAction((ActionEvent event) -> {
            Platform.exit();
            System.exit(0);            
        });

        
        
        ((StackPane)scene.getRoot()).getChildren().addAll(vbox);
        
        mainStage.setTitle("Prelucrarea Imaginilor");
        mainStage.setScene(scene);
        mainStage.show();

        mainStage.setMaximized(true);
        
        
//        // Sau varianta nr 2
//        // Get current screen of the stage
//        ObservableList<Screen> screens = Screen.getScreensForRectangle(new Rectangle2D(mainStage.getX(), mainStage.getY(), mainStage.getWidth(), mainStage.getHeight()));
//
//        // Change stage properties
//        Rectangle2D bounds = screens.get(0).getVisualBounds();
//        mainStage.setX(bounds.getMinX());
//        mainStage.setY(bounds.getMinY());
//        mainStage.setWidth(bounds.getWidth());
//        mainStage.setHeight(bounds.getHeight());

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    private BufferedImage getGray(BufferedImage image) { 

        BufferedImage g = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

        ColorConvertOp op = new ColorConvertOp(image.getColorModel().getColorSpace(), ColorSpace.getInstance(ColorSpace.CS_GRAY), null); 

        op.filter(image, g); 

        return g; 

    } 
    
    private BufferedImage equalize(BufferedImage src) { 

        BufferedImage nImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

        WritableRaster er = nImg.getRaster(); 

        int[] histogram = new int[256]; 

        for (int i = 0; i < 256; i++) { 

            histogram[i] = 0; 

        } 

        int width = src.getWidth(); 

        int height = src.getHeight(); 

        for (int i = 0; i < width; i++) { 

            for (int j = 0; j < height; j++) { 

                try { 

                    int pixel = src.getRGB(j, j); 

                    Color c = new Color(pixel, true); 

                    int nivel = c.getRed(); 

                    histogram[nivel]++; 

                } catch (Exception ex) { 

                } 

            } 

        } 

        double[] hc = new double[256]; 

        hc[0] = histogram[0]; 

        for (int i = 1; i < 256; i++) { 

            hc[i] = hc[i - 1] + histogram[i]; 

        } 

  

        for (int i = 0; i < width; i++) { 

            for (int j = 0; j < height; j++) { 

                int pixel = src.getRGB(i, j); 

                Color c = new Color(pixel, true); 

                int nivel = c.getRed(); 

                int nivel_nou = (int) ((hc[nivel] - hc[0]) * 255 / (width * height - hc[0])); 

                er.setSample(i, j, 0, nivel_nou); 

            } 

        } 

        nImg.setData(er); 

        return nImg; 

    } 
    
    
    private ChartPanel createChartPanel(BufferedImage img) { 

        // dataset 

        XYBarRenderer renderer; 

        int BINS=256;
        HistogramDataset dataset = new HistogramDataset(); 

        Raster raster = img.getRaster(); 

        final int w = img.getWidth(); 

        final int h = img.getHeight(); 

        double[] r = new double[w * h]; 

        r = raster.getSamples(0, 0, w, h, 0, r); 

        dataset.addSeries("Red", r, BINS); 

        try { 

            r = raster.getSamples(0, 0, w, h, 1, r); 

            dataset.addSeries("Green", r, BINS); 

        } catch (Exception ex) { 

        } 

        try { 

            r = raster.getSamples(0, 0, w, h, 2, r); 

            dataset.addSeries("Blue", r, BINS); 

        } catch (Exception ex) { 

        } 

        // chart 

        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value", 

                "Count", dataset, PlotOrientation.VERTICAL, true, true, false); 

        XYPlot plot = (XYPlot) chart.getPlot(); 

        renderer = (XYBarRenderer) plot.getRenderer(); 

        renderer.setBarPainter(new StandardXYBarPainter()); 

        // translucent red, green & blue 

        Paint[] paintArray = { 

            new Color(0x80ff0000, true), 

            new Color(0x8000ff00, true), 

            new Color(0x800000ff, true) 

        }; 

        plot.setDrawingSupplier(new DefaultDrawingSupplier( 

                paintArray, 

                DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE, 

                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, 

                DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE, 

                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, 

                DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE)); 

        ChartPanel panel = new ChartPanel(chart); 

        panel.setMouseWheelEnabled(true); 

        return panel; 

    } 
    
    public  BufferedImage getGrayscaleImage(BufferedImage src) { 

                BufferedImage gImg = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY); 

                WritableRaster wr = src.getRaster(); 

                WritableRaster gr = gImg.getRaster(); 

                for (int i = 0; i < wr.getWidth(); i++) { 

                    for (int j = 0; j < wr.getHeight(); j++) { 

                        gr.setSample(i, j, 0, wr.getSample(i, j, 0)); 

                    } 

                } 

                gImg.setData(gr); 

                return gImg; 

            } 
    
   
    
    public BufferedImage mediere(BufferedImage src){ 

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int k,l; 

        int w,h; 

         

        double[][] v = new double[5][5]; 

        //coeficientii mastii de filtrare  

        v[0][0]=1.0/9.0; v[0][1]=1.0/9.0; v[0][2]=1.0/9.0; v[0][3]=1.0/9.0; v[0][4]=1.0/9.0;

        v[1][0]=1.0/9.0; v[1][1]=1.0/9.0; v[1][2]=1.0/9.0; v[1][3]=1.0/9.0; v[1][4]=1.0/9.0;

        v[2][0]=1.0/9.0; v[2][1]=1.0/9.0; v[2][2]=1.0/9.0; v[2][3]=1.0/9.0;  v[2][4]=1.0/9.0; 

         

        w=src.getWidth(); 

        h=src.getHeight(); 

         

        for(i=2;i<w-2;i++){ 

            for(j=2;j<h-2;j++){ 

                //suma ponderata  

                double sumr=0,sumg=0,sumb=0; 

                for(k=-2;k<3;k++){ 

                    for(l=-2;l<3;l++){ 

                        int pixel = src.getRGB(i + k, j + l); 

                        Color c = new Color(pixel, true); 

                        sumr += v[k + 2][l + 2] * c.getRed(); 

                        sumg += v[k + 2][l + 2] * c.getGreen(); 

                        sumb += v[k + 2][l + 2] * c.getBlue(); 
                        
                        sumr=Utility.Coreacteaza(sumr);
                        sumg=Utility.Coreacteaza(sumg);
                        sumb=Utility.Coreacteaza(sumb);

                        Color nc = new Color((int) sumr, (int) sumg, (int) sumb); 

                        dst.setRGB(i, j, nc.getRGB()); 

                    } 

                } 

            } 

        } 

        return dst;         

    } 


 public  BufferedImage accentuare(BufferedImage src) { 

        BufferedImage dst = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_RGB); 

        int i,j; 

        int k,l; 

        int w,h; 

        double sumr,sumg,sumb; 

        double[][] v=new double[3][3]; 

        //coeficientii mastii  

        v[0][0]=0; v[0][1]=-1./4; v[0][2]=0; 

        v[1][0]=-1./4; v[1][1]=1; v[1][2]=-1./4; 

        v[2][0]=0; v[2][1]=-1./4; v[2][2]=0; 

        w=src.getWidth(); 

        h=src.getHeight(); 

        for(i=1;i<w-1;i++){ 

            for(j=1;j<h-1;j++){ 

                sumr=0; 

                sumg=0; 

                sumb=0; 

                for(k=-1;k<2;k++) { 

                    for(l=-1;l<2;l++) { 

                        int pixel = src.getRGB(i + k, j + l); 

                        Color c = new Color(pixel, true); 

                        sumr+=1.*v[k+1][l+1]*c.getRed(); 

                        sumg+=1.*v[k+1][l+1]*c.getGreen(); 

                        sumb+=1.*v[k+1][l+1]*c.getBlue(); 

                        int nivr=c.getRed(); 

                        //nivr=(int)(nivr+0.6*sumr); 

                        int nivg=c.getGreen(); 

                        //nivg=(int)(nivg+0.6*sumg); 

                        int nivb=c.getBlue(); 

                        //nivb=(int)(nivb+0.6*sumb); 

                        Color nc = new Color((int) Utility.adjustColor(nivr, (int) (0.6*sumr)),  

                                (int) Utility.adjustColor(nivg, (int) (0.6*sumg)),  

                                (int) Utility.adjustColor(nivb, (int) (0.6*sumb))); 

                        dst.setRGB(i, j, nc.getRGB());             

                    } 

                } 

            } 

        } 

        return dst; 

    } 

     

    

 }
