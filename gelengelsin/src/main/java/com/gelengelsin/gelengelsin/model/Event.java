package com.gelengelsin.gelengelsin.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;        // Etkinlik başlığı (örn: Sahilde Okey)
    private String location;     // Yer bilgisi
    private String description;  // Açıklama
    private String photoUrl;     // Etkinlik fotoğrafı (isteğe bağlı)
    private String category;     // Tür: oyun, sohbet, spor, vs.

    private String createdByEmail;  // Etkinliği oluşturan kişinin email'i

    private String createdAt;       // Tarih (şimdilik String yeter)

    // Gelişmiş versiyonda → @ManyToOne User creator yapılabilir
}
