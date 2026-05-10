package vlb.developer.profile.dtos;

import vlb.developer.entities.ClientEnty;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ClientDto {

    public UUID id;
    public String name;
    public String nickname;
    public String username;
    public String email;
    public String countryCode;
    public String phoneNumber;
    public OffsetDateTime createdAt;

    public static ClientDto from(ClientEnty client) {
        ClientDto dto = new ClientDto();
        dto.id = client.getId();
        dto.name = client.getName();
        dto.nickname = client.getNickname();
        dto.username = client.getUsername();
        dto.email = client.getEmail();
        dto.countryCode = client.getCountryCode();
        dto.phoneNumber = client.getPhoneNumber();
        dto.createdAt = client.getCreatedAt();
        return dto;
    }
}
