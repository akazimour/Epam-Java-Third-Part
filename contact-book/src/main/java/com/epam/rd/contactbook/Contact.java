package com.epam.rd.contactbook;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Contact {

    private String name;
    private static ContactInfo [] phoneNumber = new ContactInfo[1];
    private static Email [] emails = new Email[3];
    private static Social [] socialLinks = new Social[5];
    private static int counter = 0;
    private static int socCounter = 0;
    private int maxSize= 0;


    public Contact(String contactName) {
        makeEmpty(phoneNumber);
        makeEmpty(emails);
        makeEmpty(socialLinks);
        this.name = contactName;
    }

    public void rename(String newName) {
        if (newName==null || newName.isBlank()==true) {
            return;
        }
        this.name = newName;
    }
        public Email addEmail(String localPart, String domain) {
        if(localPart.isBlank() || localPart == null || domain.isBlank() || domain == null) {
            return null;
        }if (isFull(emails)==true){
                return null;
            }
            Email response = new Email();
            String result = localPart + "@" + domain;
            response.setEmail(result);
            response.setTitle("Email");
            return insertEmail(response);
        }

    public Email addEpamEmail(String firstname, String lastname) {
        if (firstname.isBlank() || firstname == null || lastname.isBlank() || lastname == null) {
            return null;
        }
        if (isFull(emails) == true) {
            return null;
        }
        String result = firstname + "_" + lastname + "@" + "epam.com";

        return insertEmail(makeAnon(result,"Epam Email"));
        }

    public ContactInfo addPhoneNumber(int code, String number) {
        if (isFull(phoneNumber)==true){
            return null;
        }
        if (number.isBlank() || number == null){
            return null;
        }
        String result = "+"+code+" "+number;
        ContactInfo contactInfo = new ContactInfo() {
            @Override
            public String getTitle() {
                return "Tel";
            }
            @Override
            public String getValue() {
                return result;
            }
        };
        return phoneNumber[0]=contactInfo;
    }

    public Social addTwitter(String twitterId) {
        if (twitterId==null || twitterId.isBlank()){return null;
        }
        if (isFull(socialLinks)==true){
            return null;
        }
        String result = twitterId;

        Social soc = new Social();
        soc.setSocialLink(result);
        soc.setId("Twitter");
        insertSocial(soc);
        return soc;
    }

    public Social addInstagram(String instagramId) {
        if (instagramId==null || instagramId.isBlank()){return null;
        }
        if (isFull(socialLinks)==true){
            return null;
        }
        String result = instagramId;
        Social soc = new Social();
        soc.setSocialLink(result);
        soc.setId("Instagram");
        insertSocial(soc);
        return soc;
    }
    public Social addSocialMedia(String title, String id) {
        if (title==null || title.isBlank() || id==null || id.isBlank() ){return null;
        }
        if (isFull(socialLinks)==true){
            return null;
        }
        String result = id;
        Social soc = new Social();
        soc.setSocialLink(result);
        soc.setId(title);
        insertSocial(soc);
        return soc;
    }
    public ContactInfo[] getInfo() {
        NameContactInfo nameContactInfo = new NameContactInfo();
        ContactInfo[] copyOfPhoneName = new ContactInfo[1];
        copyOfPhoneName[0]=nameContactInfo;

        int phoneNumCount = getCount(phoneNumber);
        ContactInfo[] copyOfPhoneNumber = new ContactInfo[phoneNumCount];
        System.arraycopy(phoneNumber,0,copyOfPhoneNumber,0,phoneNumCount);

        int emailCount = getCount(emails);
        ContactInfo[] copyOfEmails = new ContactInfo[emailCount];
        System.arraycopy(emails,0,copyOfEmails,0,emailCount);

        int socialLinkCount = getCount(socialLinks);
        ContactInfo[] copyOfSocialLinks = new ContactInfo[socialLinkCount];
        System.arraycopy(socialLinks,0,copyOfSocialLinks,0,socialLinkCount);

        maxSize = copyOfPhoneName.length + copyOfPhoneNumber.length+copyOfEmails.length + copyOfSocialLinks.length;

        ContactInfo[] contactInfos = new ContactInfo[maxSize];
        System.arraycopy(copyOfPhoneName,0,contactInfos,0,copyOfPhoneName.length);
        int count = getCount(contactInfos);
        count = getCount(createInfoSummary(copyOfPhoneNumber,contactInfos,count));
        count = getCount(createInfoSummary(copyOfEmails,contactInfos,count));
        createInfoSummary(copyOfSocialLinks,contactInfos,count);

        counter=0;
        socCounter=0;
        maxSize = 0;
        return contactInfos;
        }
        public static Email makeAnon(String val, String tit ){
        Email anonMail = new Email(){
            @Override
                public String getValue() {
                    return val;
                }
                @Override
                public String getTitle() {
                    return tit;
                }
        };
        return anonMail;
    }
    public static void makeEmpty(ContactInfo[] array){
        for (int i=0; i<array.length; i++)
            array[i] = null;
    }

    private static ContactInfo[] createInfoSummary(ContactInfo[] infoArray,ContactInfo[] destinationArray, int from){
        if (infoArray.length > 0) {
            System.arraycopy(infoArray, 0, destinationArray, from, infoArray.length);
            return destinationArray;
        }else
            return destinationArray;
    }

    private static int getCount(ContactInfo[] arr){
        int notNullCounter = 0;
        for (int k = 0; k < arr.length; k++) {
            if (arr[k] == null ){
                continue;
            }
            notNullCounter++;
        }
        return notNullCounter;
    }
    private static Email insertEmail(Email response) {
        while (emails[counter]!=null && counter< emails.length){
            if (counter == socialLinks.length-1){return null;}
            else {
                counter++;
            }
        }
        emails[counter++]=response;
        return response;
    }
    public Social insertSocial(Social social){
        while (socialLinks[socCounter]!=null && counter< socialLinks.length){
            if (socCounter == socialLinks.length-1){
                return null;
            }
            else {
                socCounter++;
            }
        }
        socialLinks[socCounter++]=social;
        return social;
    }

    public static boolean isFull(Object[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                return false;
            }
        }return true;
    }
    private class NameContactInfo implements ContactInfo{

        @Override
        public String getTitle() {
           return "Name";
        }
        @Override
        public String getValue() {
            return name;
        }
    }
    public static class Email implements ContactInfo{
        private String email;
        private String title;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setEmail(String email) {
            this.email = email;
        }
        @Override
        public String getTitle() {
            return title;
        }
        @Override
        public String getValue() {
            return email;
        }
    }
      public static class Social implements ContactInfo{
          private String socialLink;
          private String id;
          public void setId(String id) {
              this.id = id;
          }

          public String getSocialLink() {
              return socialLink;
          }

          public void setSocialLink(String socialLink) {
              this.socialLink = socialLink;
          }

          @Override
          public String getTitle() {
              return id;
          }

          @Override
          public String getValue() {
              return socialLink;
          }
      }
}
