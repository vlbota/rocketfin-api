package vlb.developer.utils.environments;


import org.eclipse.microprofile.config.ConfigProvider;

public class Comunications {

    public static final String BOT_TELEGRAM = ConfigProvider.getConfig().getValue("env.telegram.bot",String.class);

    public static final String BOT_CHAT_MESSAGE_FAILS = ConfigProvider.getConfig().getValue("env.telegram.chat.message-fails",String.class);
}
