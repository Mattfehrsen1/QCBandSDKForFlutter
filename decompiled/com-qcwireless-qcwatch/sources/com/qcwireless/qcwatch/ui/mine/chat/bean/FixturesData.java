package com.qcwireless.qcwatch.ui.mine.chat.bean;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

/* loaded from: classes3.dex */
abstract class FixturesData {
    static SecureRandom rnd = new SecureRandom();
    static ArrayList<String> avatars = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.1
        {
            add("http://i.imgur.com/pv1tBmT.png");
        }
    };
    static final ArrayList<String> groupChatImages = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.2
        {
            add("http://i.imgur.com/hRShCT3.png");
        }
    };
    static final ArrayList<String> groupChatTitles = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.3
        {
            add("ChatGPT");
        }
    };
    static final ArrayList<String> names = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.4
        {
            add("ChatGPT AI");
        }
    };
    static final ArrayList<String> messages = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.5
        {
            add("Hello!");
        }
    };
    static final ArrayList<String> images = new ArrayList<String>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.bean.FixturesData.6
        {
            add("https://habrastorage.org/getpro/habr/post_images/e4b/067/b17/e4b067b17a3e414083f7420351db272b.jpg");
            add("https://cdn.pixabay.com/photo/2017/12/25/17/48/waters-3038803_1280.jpg");
        }
    };

    FixturesData() {
    }

    static String getRandomId() {
        return Long.toString(UUID.randomUUID().getLeastSignificantBits());
    }

    static String getRandomAvatar() {
        ArrayList<String> arrayList = avatars;
        return arrayList.get(rnd.nextInt(arrayList.size()));
    }

    static String getRandomGroupChatImage() {
        ArrayList<String> arrayList = groupChatImages;
        return arrayList.get(rnd.nextInt(arrayList.size()));
    }

    static String getRandomGroupChatTitle() {
        ArrayList<String> arrayList = groupChatTitles;
        return arrayList.get(rnd.nextInt(arrayList.size()));
    }

    static String getRandomName() {
        ArrayList<String> arrayList = names;
        return arrayList.get(rnd.nextInt(arrayList.size()));
    }

    static String getRandomMessage() {
        return messages.get(0);
    }

    static String getRandomImage() {
        ArrayList<String> arrayList = images;
        return arrayList.get(rnd.nextInt(arrayList.size()));
    }

    static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }
}
