package com.youthdiversion.calendar;

import android.content.ContextWrapper;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

/**
 * Created by Andrew Storus on 2016-02-06.
 */
public class ConfigEmail {
    private static String SenderEmailAddress = "ctcfeb09@gmail.com";
    private static String ReceiverEmailAddress;
    private static String emailPassword = "4plL1lkM";
    private static String emailSubject;
    private static String attachmentPath;
    private static Multipart multipart;

    public static Multipart getMultipart() throws IOException{
        Multipart multipart = new MimeMultipart();
        File f = new File(attachmentPath);
        MimeBodyPart attachmentPart = new MimeBodyPart();
        try{
            attachmentPart.attachFile(f);
            multipart.addBodyPart(attachmentPart);
        }catch (Exception e){
            System.out.println("Error in reading file");
        }

        return multipart;
    }


    public static String getAttachmentPath(){
        String atchPath = new String (attachmentPath);
        return atchPath;
    }

    public static void setAttachmentPath(String eAtt){
        attachmentPath = eAtt;
    }

    public static String getReceiverEmailAddress(){
        String eAdd = new String(ReceiverEmailAddress);
        return eAdd;
    }

    public static String getSenderEmailAddress(){
        String eAdd = new String(SenderEmailAddress);
        return eAdd;
    }

    public static void setReceiverEmailAddress(String eAddr){
        ReceiverEmailAddress = new String(eAddr);
    }

    public static void setSenderEmailAddress(String eAddr){
        SenderEmailAddress = new String(eAddr);
    }

    public static String getEmailPassword() {
        String ePass = new String(emailPassword);
        return ePass;
    }

    public static void setEmailPassword(String ePass){
        emailPassword = new String(ePass);
    }

    public static void setEmailSubject(String eSub){
        emailSubject = new String(eSub);
    }

    public static String getEmailSubject() {
        String eSub = new String(emailSubject);
        return eSub;
    }


}
