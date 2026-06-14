package com.farmacia.msreportes.model;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String tipo;

    @Column(nullable = false)
    private LocalDateTime generadoEn;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @PrePersist
    public void prePersist() {
        this.generadoEn = LocalDateTime.now();
    }
}


