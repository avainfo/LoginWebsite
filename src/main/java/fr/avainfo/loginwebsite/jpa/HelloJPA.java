package fr.avainfo.loginwebsite.jpa;

import jakarta.persistence.*;
import jdk.jfr.Name;

@Entity @Table(name = "Test")
public class HelloJPA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
