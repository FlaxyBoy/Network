package ua.dream.chat.network.netty.frame;

import ua.dream.chat.network.netty.packet.out.*;

public interface PacketOutHandler extends PacketHandler {

    void handle(PacketOut3RegistrationFailed packet);

    void handle(PacketOut4LoginFailed packet);

    void handle(PacketOut5UserData packet);

    void handle(PacketOut6UserLogout packet);

    void handle(PacketOut8Message packet);

}
