package com.ifce.dailypoint.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum TipoPontoEnum {
    
    ENTRADA_MANHA("Entrada da manhã"),
    ALMOCO("Saída para almoço"),
    ENTRADA_TARDE("Entrada da tarde"),
    SAIDA("Saída");

    private String descricao;
}
