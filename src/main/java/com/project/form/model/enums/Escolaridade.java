package com.project.form.model.enums;

import lombok.Getter;

@Getter
public enum Escolaridade {

    FUNDAMENTAL_INCOMPLETO("FUNDAMENTAL_INCOMPLETO"),
    FUNDAMENTAL("FUNDAMENTAL"),
    MEDIO_INCOMPLETO("MEDIO_INCOMPLETO"),
    MEDIO("MEDIO"),
    SUPERIOR_INCOMPLETO("SUPERIOR_INCOMPLETO"),
    SUPERIOR("SUPERIOR");

    private final String value;

    Escolaridade(String value) {
        this.value = value;
    }

    public static Escolaridade valueOfString(String valor) {
        for (Escolaridade escolaridade : values()) {
            if (escolaridade.getValue().equals(valor)) {
                return escolaridade;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Escolaridade: " + valor);
    }
}
