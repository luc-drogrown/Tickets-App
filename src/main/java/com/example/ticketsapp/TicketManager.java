package com.example.ticketsapp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.spire.barcode.BarCodeGenerator;
import com.spire.barcode.BarCodeType;
import com.spire.barcode.BarcodeSettings;
import com.spire.barcode.QRCodeECL;

import javax.imageio.ImageIO;


public class TicketManager
{
    //methods
    public void genBarcode(String userName, String eventName) throws IOException
    {
        BarcodeSettings settings = new BarcodeSettings();
        settings.setType(BarCodeType.QR_Code);
        String data = "https://github.com/luc-drogrown";
        settings.setData(data);
        settings.setX(4);
        settings.setQRCodeECL(QRCodeECL.M);

        //set text
        settings.setTopText(userName);
        settings.setBottomText(eventName);
        settings.setShowText(false);
        settings.setShowTopText(true);
        settings.setShowTextOnBottom(true);

        settings.hasBorder(false);
        BarCodeGenerator barCodeGenerator = new BarCodeGenerator(settings);
        BufferedImage bufferedImage = barCodeGenerator.generateImage();
        ImageIO.write(bufferedImage,"png", new File(userName+".png"));
    }
}
