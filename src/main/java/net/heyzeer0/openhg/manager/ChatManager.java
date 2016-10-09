package net.heyzeer0.openhg.manager;

/**
 * Created by HeyZeer0 on 09/10/2016.
 */
public class ChatManager {

    private static boolean ativado = true;

    public static boolean isActive() {
        return ativado;
    }

    public static void setChat(boolean value) {
        ativado = value;
    }

}
